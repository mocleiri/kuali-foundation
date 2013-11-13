<?php
# Variables
$section = 'keypersonnel';
$page = 'keypersonnel-intelcredit';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

<script type="text/javascript">
    /* Google charts */
    google.load('visualization', '1.0', {'packages':['corechart']});
    google.setOnLoadCallback(drawChart);
    
    function drawChart(value1, value2) {
        var data_recognition = new google.visualization.DataTable();
        data_recognition.addColumn('string', 'Name');
        data_recognition.addColumn('number', 'Percentage');
        data_recognition.addRows([
            ['Edward H Haskell', 50],
            ['Ward Cleaver', 50]
        ]);

        var data_responsibility = new google.visualization.DataTable();
        data_responsibility.addColumn('string', 'Name');
        data_responsibility.addColumn('number', 'Percentage');
        data_responsibility.addRows([
            ['Edward H Haskell', 50],
            ['Ward Cleaver', 50]
        ]);

        var data_space = new google.visualization.DataTable();
        data_space.addColumn('string', 'Name');
        data_space.addColumn('number', 'Percentage');
        data_space.addRows([
            ['Edward H Haskell', 50],
            ['Ward Cleaver', 50]
        ]);

        var data_financial = new google.visualization.DataTable();
        data_financial.addColumn('string', 'Name');
        data_financial.addColumn('number', 'Percentage');
        data_financial.addRows([
            ['Edward H Haskell', 50],
            ['Ward Cleaver', 50]
        ]);

        var options_recognition = {'title':'Recognition',
                       'width': 290,
                       'height': 'auto'
                    };

        var options_responsibiltiy = {'title':'Responsibility',
                       'width': 290,
                       'height': 'auto'
                    };

        var options_space = {'title':'Space',
                       'width': 290,
                       'height': 'auto'
                    };

        var options_financial = {'title':'Financial',
                       'width': 290,
                       'height': 'auto'
                    };

        var chart_recognition = new google.visualization.PieChart(document.getElementById('credit-allocation-chart-recognition'));
        var chart_responsibility = new google.visualization.PieChart(document.getElementById('credit-allocation-chart-responsibility'));
        var chart_space = new google.visualization.PieChart(document.getElementById('credit-allocation-chart-space'));
        var chart_financial = new google.visualization.PieChart(document.getElementById('credit-allocation-chart-financial'));

        chart_recognition.draw(data_recognition, options_recognition);
        chart_responsibility.draw(data_responsibility, options_responsibiltiy);
        chart_space.draw(data_space, options_space);
        chart_financial.draw(data_financial, options_financial);
    }
</script>

<section id="main">
  <?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
  <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
    <div class="container-fluid" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper">

                   
        <h3 class="pull-left"> Credit Allocation</h3>
        
        <!-- Credit allocation -->

        <div class="credit-allocation">
            <div class="credit-allocation-wrapper clearfix">
                <div class="credit-allocation-pie-charts">
                    <div class="credit-allocation-pie-chart-wrapper">
                        <div class="ca-chart-large">
                            <div id="credit-allocation-chart-recognition"></div>
                        </div>
                        <div class="ca-chart-large">
                            <div id="credit-allocation-chart-responsibility"></div>
                        </div>
                        <div class="ca-chart-large">
                            <div id="credit-allocation-chart-space"></div>
                        </div>
                        <div class="ca-chart-large">
                            <div id="credit-allocation-chart-financial"></div>
                        </div>
                    </div>
                </div>

                <div class="credit-allocation-data-table">
                    
                    <div class="credit-allocation-header-row clearfix">
                        <div class="credit-person">&nbsp;</div>
                        <div class="credit-recognition">
                            Recognition
                        </div>
                        <div class="credit-responsibility">
                            Responsibility
                        </div>
                        <div class="credit-space">
                            Space
                        </div>
                        <div class="credit-financial">
                            Financial
                        </div>
                    </div>

                    <div class="credit-allocation-collapsible-row clearfix opened" id="allocation_01" data-allocation-person="Edward H Haskell">
                        
                        <!-- Main person row -->
                        <div class="credit-allocation-data-row clearfix">
                            <div class="credit-person">
                                <h3><a href="#"><i class="icon icon-angle-down"></i> Edward H Haskell</a></h3>
                            </div>
                            <div class="credit-recognition">
                                <div class="credit-allocation-cell">
                                    <label class="off-screen" for="a1-1">Recognition allocation for Edward H Haskell</label>
                                    <input type="text" class="credit-allocation-text" id="a1-1" value="50" />
                                </div>
                                <div class="credit-allocation-cell">
                                    <div class="credit-allocation-slider-control"></div>
                                </div>
                            </div>
                            <div class="credit-responsibility">
                                <div class="credit-allocation-cell">
                                    <label class="off-screen" for="a1-2">Responsibilty allocation for Edward H Haskell</label>
                                    <input type="text" class="credit-allocation-text" id="a1-2" value="50" />
                                </div>
                                <div class="credit-allocation-cell">
                                    <div class="credit-allocation-slider-control"></div>
                                </div>
                            </div>
                            <div class="credit-space">
                                <div class="credit-allocation-cell">
                                    <label class="off-screen" for="a1-3">Space allocation for Edward H Haskell</label>
                                    <input type="text" class="credit-allocation-text" id="a1-3" value="50" />
                                </div>
                                <div class="credit-allocation-cell">
                                    <div class="credit-allocation-slider-control"></div>
                                </div>
                            </div>
                            <div class="credit-financial">
                                <div class="credit-allocation-cell">
                                    <label class="off-screen" for="a1-4">Financial allocation for Edward H Haskell</label>
                                    <input type="text" class="credit-allocation-text" id="a1-4" value="50" />
                                </div>
                                <div class="credit-allocation-cell">
                                    <div class="credit-allocation-slider-control"></div>
                                </div>
                            </div>
                        </div>

                        <!-- Sub-rows -->
                        <div class="credit-allocation-data-row clearfix">
                            <div class="credit-subcat">BL - IIDC - IND INST ON DISABILITY/COMMNTY</div>
                            <div class="credit-recognition">
                                <div class="credit-allocation-cell">
                                    <label class="off-screen" for="c1-1">Recognition allocation for Edward H Haskell</label>
                                    <input type="text" class="credit-allocation-text" id="c1-1" value="50" />
                                </div>
                                <div class="credit-allocation-cell">
                                    <div class="credit-allocation-slider-control"></div>
                                </div>
                            </div>
                            <div class="credit-responsibility">
                                <div class="credit-allocation-cell">
                                    <label class="off-screen" for="c1-2">Responsibilty allocation for Edward H Haskell</label>
                                    <input type="text" class="credit-allocation-text" id="c1-2" value="50" />
                                </div>
                                <div class="credit-allocation-cell">
                                    <div class="credit-allocation-slider-control"></div>
                                </div>
                            </div>
                            <div class="credit-space">
                                <div class="credit-allocation-cell">
                                    <label class="off-screen" for="c1-3">Space allocation for Edward H Haskell</label>
                                    <input type="text" class="credit-allocation-text" id="c1-3" value="50" />
                                </div>
                                <div class="credit-allocation-cell">
                                    <div class="credit-allocation-slider-control"></div>
                                </div>
                            </div>
                            <div class="credit-financial">
                                <div class="credit-allocation-cell">
                                    <label class="off-screen" for="c1-4">Financial allocation for Edward H Haskell</label>
                                    <input type="text" class="credit-allocation-text" id="c1-4" value="50" />
                                </div>
                                <div class="credit-allocation-cell">
                                    <div class="credit-allocation-slider-control"></div>
                                </div>
                            </div>
                        </div>

                        <!-- Sub-rows -->
                        <div class="credit-allocation-data-row clearfix">
                            <div class="credit-subcat">00001 - University</div>
                            <div class="credit-recognition">
                                <div class="credit-allocation-cell">
                                    <label class="off-screen" for="d1-1">Recognition allocation for Edward H Haskell</label>
                                    <input type="text" class="credit-allocation-text" id="d1-1" value="50" />
                                </div>
                                <div class="credit-allocation-cell">
                                    <div class="credit-allocation-slider-control"></div>
                                </div>
                            </div>
                            <div class="credit-responsibility">
                                <div class="credit-allocation-cell">
                                    <label class="off-screen" for="d1-2">Responsibilty allocation for Edward H Haskell</label>
                                    <input type="text" class="credit-allocation-text" id="d1-2" value="50" />
                                </div>
                                <div class="credit-allocation-cell">
                                    <div class="credit-allocation-slider-control"></div>
                                </div>
                            </div>
                            <div class="credit-space">
                                <div class="credit-allocation-cell">
                                    <label class="off-screen" for="d1-3">Space allocation for Edward H Haskell</label>
                                    <input type="text" class="credit-allocation-text" id="d1-3" value="50" />
                                </div>
                                <div class="credit-allocation-cell">
                                    <div class="credit-allocation-slider-control"></div>
                                </div>
                            </div>
                            <div class="credit-financial">
                                <div class="credit-allocation-cell">
                                    <label class="off-screen" for="d1-4">Financial allocation for Edward H Haskell</label>
                                    <input type="text" class="credit-allocation-text" id="d1-4" value="50" />
                                </div>
                                <div class="credit-allocation-cell">
                                    <div class="credit-allocation-slider-control"></div>
                                </div>
                            </div>
                        </div>

                    </div>

                    <div class="credit-allocation-collapsible-row clearfix closed" id="allocation_02" data-allocation-person="Ward Cleaver">

                        <div class="credit-allocation-data-row clearfix">
                            <div class="credit-person">
                                <h3><a href="#"><i class="icon icon-angle-right"></i> Ward Cleaver</a></h3>
                            </div>
                            <div class="credit-recognition">
                                <div class="credit-allocation-cell">
                                    <label class="off-screen" for="b1-1">Recognition allocation for Ward Cleaver</label>
                                    <input type="text" class="credit-allocation-text ca-trans" id="b1-1" value="50" />
                                    <!-- <span id="b1-1">50</span> -->
                                </div>
                                <div class="credit-allocation-cell">
                                    <div class="credit-allocation-slider-control"></div>
                                </div>
                            </div>
                            <div class="credit-responsibility">
                                <div class="credit-allocation-cell">
                                    <label class="off-screen" for="b1-2">Responsibilty allocation for Ward Cleaver</label>
                                    <input type="text" class="credit-allocation-text ca-trans" id="b1-2" value="50" />
                                    <!-- <span id="b1-2">50</span> -->
                                </div>
                                <div class="credit-allocation-cell">
                                    <div class="credit-allocation-slider-control"></div>
                                </div>
                            </div>
                            <div class="credit-space">
                                <div class="credit-allocation-cell">
                                    <label class="off-screen" for="b1-3">Space allocation for Ward Cleaver</label>
                                    <input type="text" class="credit-allocation-text ca-trans" id="b1-3" value="50" />
                                    <!-- <span id="b1-3">50</span> -->
                                </div>
                                <div class="credit-allocation-cell">
                                    <div class="credit-allocation-slider-control"></div>
                                </div>
                            </div>
                            <div class="credit-financial">
                                <div class="credit-allocation-cell">
                                    <label class="off-screen" for="b1-4">Financial allocation for Ward Cleaver</label>
                                    <input type="text" class="credit-allocation-text ca-trans" id="b1-4" value="50" />
                                    <!-- <span id="b1-4">50</span> -->
                                </div>
                                <div class="credit-allocation-cell">
                                    <div class="credit-allocation-slider-control"></div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="credit-allocation-footer-row clearfix">
                        <div class="credit-person">
                            Totals
                        </div>
                        <div class="credit-recognition">
                            <span id="credit-allocation-totals-recognition">100</span>%
                        </div>
                        <div class="credit-responsibility">
                            <span id="credit-allocation-totals-responsibility">100</span>%
                        </div>
                        <div class="credit-space">
                            <span id="credit-allocation-totals-space">100</span>%
                        </div>
                        <div class="credit-financial">
                            <span id="credit-allocation-totals-financial">100</span>%
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <!-- // -->

        <div class="uif-stickyFooter uif-stickyButtonFooter">
        <div class="btn-row-page-action"> <button  onclick="location.href='prop.keypersonnel.start.php'" class="btn btn-default">Back</button>
<button class="btn btn-default">Save</button>
      <button  onclick="location.href='prop.keypersonnel.creditfa.php'" class="btn btn-primary">Save and Continue</button>
            
        </div>
        <!-- // -->

        </div>
      </div>
    </div>
  </div>
</section>


<script type="text/javascript">
$(document).ready(function() {

    /*
     * Scripting for credit allocation functionality.
     * To be moved to main scripting file once it's complete and approved.
     * Chris Rodriguez, clrux
    */

    $('.credit-allocation-collapsible-row.closed .credit-allocation-slider-control').hide();

    /* Row collapsing */
    $('.credit-allocation-data-row h3 a').click(function(e) {

        e.preventDefault();

        if ($(this).parent().parent().parent().parent().hasClass('opened')) {
            
            // Do nothing

        } else {

            $('.credit-allocation-collapsible-row').removeClass('opened').addClass('closed');
            $('.credit-allocation-collapsible-row h3 a .icon').removeClass('icon-angle-down').addClass('icon-angle-right');
            $('.credit-allocation-collapsible-row.closed .credit-allocation-cell input').each(function() {
                // $(this).replaceWith('<span id="' + $(this).attr('id') + '">' + $(this).val() + '</span>');
                $(this).addClass('ca-trans');
            });
            $('.credit-allocation-collapsible-row.closed .credit-allocation-slider-control').hide();

            $(this).parent().parent().parent().parent().removeClass('closed').addClass('opened');
            $(this).find('.icon').removeClass('icon-angle-right').addClass('icon-angle-down');
            $('.credit-allocation-collapsible-row.opened .credit-allocation-cell input').each(function() {
                // $(this).replaceWith('<input type="text" class="credit-allocation-text" id="' + $(this).attr('id') + '" value="' + $(this).text() + '" />');
                $(this).removeClass('ca-trans');
            });
            $('.credit-allocation-collapsible-row.opened .credit-allocation-slider-control').show();

        }

        return false;
    });

    /*
     * Column math
     * Column names: credit-recognition, credit-responsibility, credit-space, credit-financial
    */
    function update_totals() {
        var recognition_total       = $('#credit-allocation-totals-recognition'), 
            responsibility_total    = $('#credit-allocation-totals-responsibility'), 
            space_total             = $('#credit-allocation-totals-space'), 
            financial_total         = $('#credit-allocation-totals-financial'), 
            recognition_column      = $('.credit-recognition'), 
            responsibility_column   = $('.credit-responsibility'), 
            space_column            = $('.credit-space'), 
            financial_column        = $('.credit-financial');

        recognition_total.text( (+$('#a1-1').val() + +$('#b1-1').val()) );
        responsibility_total.text( (+$('#a1-2').val() + +$('#b1-2').val()) );
        space_total.text( (+$('#a1-3').val() + +$('#b1-3').val()) );
        financial_total.text( (+$('#a1-4').val() + +$('#b1-4').val()) );

        <!-- -->
        var data_recognition = new google.visualization.DataTable();
        data_recognition.addColumn('string', 'Name');
        data_recognition.addColumn('number', 'Percentage');
        data_recognition.addRows([
            ['Edward H Haskell', +$('#a1-1').val()],
            ['Ward Cleaver', +$('#b1-1').val()]
        ]);

        var data_responsibility = new google.visualization.DataTable();
        data_responsibility.addColumn('string', 'Name');
        data_responsibility.addColumn('number', 'Percentage');
        data_responsibility.addRows([
            ['Edward H Haskell', +$('#a1-2').val()],
            ['Ward Cleaver', +$('#b1-2').val()]
        ]);

        var data_space = new google.visualization.DataTable();
        data_space.addColumn('string', 'Name');
        data_space.addColumn('number', 'Percentage');
        data_space.addRows([
            ['Edward H Haskell', +$('#a1-3').val()],
            ['Ward Cleaver', +$('#b1-3').val()]
        ]);

        var data_financial = new google.visualization.DataTable();
        data_financial.addColumn('string', 'Name');
        data_financial.addColumn('number', 'Percentage');
        data_financial.addRows([
            ['Edward H Haskell', +$('#a1-4').val()],
            ['Ward Cleaver', +$('#b1-4').val()]
        ]);

        var options_recognition = {'title':'Recognition',
                       'width': 290,
                       'height': 'auto'
                    };

        var options_responsibiltiy = {'title':'Responsibility',
                       'width': 290,
                       'height': 'auto'
                    };

        var options_space = {'title':'Space',
                       'width': 290,
                       'height': 'auto'
                    };

        var options_financial = {'title':'Financial',
                       'width': 290,
                       'height': 'auto'
                    };

        var chart_recognition = new google.visualization.PieChart(document.getElementById('credit-allocation-chart-recognition'));
        var chart_responsibility = new google.visualization.PieChart(document.getElementById('credit-allocation-chart-responsibility'));
        var chart_space = new google.visualization.PieChart(document.getElementById('credit-allocation-chart-space'));
        var chart_financial = new google.visualization.PieChart(document.getElementById('credit-allocation-chart-financial'));

        chart_recognition.draw(data_recognition, options_recognition);
        chart_responsibility.draw(data_responsibility, options_responsibiltiy);
        chart_space.draw(data_space, options_space);
        chart_financial.draw(data_financial, options_financial);
        <!-- // -->

        if (recognition_total.text() > 100) {
            recognition_total.parent().addClass('ca-error');
        } else {
            recognition_total.parent().removeClass('ca-error');
        }

        if (responsibility_total.text() > 100) {
            responsibility_total.parent().addClass('ca-error');
        } else {
            responsibility_total.parent().removeClass('ca-error');
        }

        if (space_total.text() > 100) {
            space_total.parent().addClass('ca-error');
        } else {
            space_total.parent().removeClass('ca-error');
        }

        if (financial_total.text() > 100) {
            financial_total.parent().addClass('ca-error');
        } else {
            financial_total.parent().removeClass('ca-error');
        }
    }

    $('.credit-allocation-data-table input').keyup(function() {
        update_totals();
    });

    $('.credit-allocation-slider-control').slider({
        change: function(event, ui) {
            update_totals();
        }
    });

    $('#credit-allocation-totals-recognition').text();   

});
</script>



<script>
$('#myTab a').click(function (e) {
  e.preventDefault()
  $(this).tab('show')
})
</script>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>
