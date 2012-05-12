package org.kuali.maven.ec2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.kuali.maven.ec2.pojo.Column;
import org.kuali.maven.ec2.pojo.Row;
import org.kuali.maven.ec2.pojo.RowComparator;
import org.kuali.maven.ec2.pojo.Table;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.Tag;

/**
 *
 * @goal describeinstances
 */
public class DescribeInstancesMojo extends AbstractEC2Mojo {

    /**
     * @parameter expression="${ec2.displayTag}" default-value="Name"
     */
    private String displayTag;

    @Override
    public void execute() throws MojoExecutionException {
        AmazonEC2 client = getEC2Client();
        DescribeInstancesResult result = client.describeInstances();
        List<Instance> instances = getInstances(result.getReservations());
        Table table = getTable(instances);
        getLog().info(getDisplay(table.getColumns()));
        for (Row row : table.getRows()) {
            getLog().info(getDisplay(table, row));
        }
    }

    protected String getDisplay(List<Column> columns) {
        StringBuilder sb = new StringBuilder();
        for (Column c : columns) {
            sb.append(StringUtils.rightPad(c.getTitle(), c.getWidth(), " "));
        }
        return sb.toString();
    }

    protected String getDisplay(Table table, Row row) {
        StringBuilder sb = new StringBuilder();
        List<String> elements = row.getElements();
        for (int i = 0; i < elements.size(); i++) {
            int width = table.getColumns().get(i).getWidth();
            sb.append(StringUtils.rightPad(elements.get(i), width, " "));
        }
        return sb.toString();
    }

    protected Table getTable(List<Instance> instances) {
        Table table = new Table();
        List<Row> rows = new ArrayList<Row>();
        for (Instance i : instances) {
            Row row = new Row();
            List<String> elements = new ArrayList<String>();
            elements.add(getName(i));
            elements.add(i.getInstanceId());
            row.setElements(elements);
            rows.add(row);
        }
        Collections.sort(rows, new RowComparator());
        Collections.reverse(rows);
        table.setRows(rows);
        List<Column> columns = new ArrayList<Column>();
        Column column1 = new Column();
        Column column2 = new Column();
        column1.setTitle(displayTag);
        column2.setTitle("Instance");
        column1.setWidth(column1.getTitle().length());
        column2.setWidth(column2.getTitle().length());
        columns.add(column1);
        columns.add(column2);
        table.setColumns(columns);
        for (int i = 0; i < rows.size(); i++) {
            List<String> elements = rows.get(i).getElements();
            for (int j = 0; j < elements.size(); j++) {
                Column c = columns.get(j);
                c.setWidth(Math.max(c.getWidth(), elements.get(j).length()));
            }
        }
        return table;
    }

    protected String getName(Instance i) {
        List<Tag> tags = i.getTags();
        for (Tag t : tags) {
            if (t.getKey().equals(displayTag)) {
                return t.getValue();
            }
        }
        return "";
    }

    protected List<Instance> getInstances(List<Reservation> reservations) {
        List<Instance> instances = new ArrayList<Instance>();
        for (Reservation r : reservations) {
            instances.addAll(r.getInstances());
        }
        return instances;
    }

    public String getDisplayTag() {
        return displayTag;
    }

    public void setDisplayTag(String displayTag) {
        this.displayTag = displayTag;
    }

}
