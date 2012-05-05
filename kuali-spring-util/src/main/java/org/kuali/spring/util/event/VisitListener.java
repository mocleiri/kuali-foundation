package org.kuali.spring.util.event;

public interface VisitListener {
    public void beforeVisit(PropertyValueVisitEvent event);

    public void afterVisit(PropertyValueVisitEvent event);

    public void beforeVisit(BeanVisitEvent event);

    public void afterVisit(BeanVisitEvent event);

    public void valueResolved(ValueResolutionEvent event);
}
