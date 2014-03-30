/*
 * Javascript for POC prototype
 * clrux
 */

jQuery(document).ready(function($) {

    /*
     * Editable rows in datatables
     * Requires: table tr td
     */

    $('table tr td').on('click', '.icon-edit', function() {

        var that = $(this);
        var editable_inputs = that.parent().parent().find('td');

        editable_inputs.each(function() {
            if ($(this).is(':last-child') || ($(this).hasClass('not-editable'))) {

            } else {

                var current_value = $(this).text();
                $(this).html('<input type="text" name="asdf" id="asdf" value="' + current_value + '" size="3" />');

            }
        });

        that.parent().parent().find('td:last').html('<a class="icon-save" href="#"><span class="sr-only">Save</span></a>');
        that.parent().parent().addClass('uif-new-row'); // Not working for some reason

    });

    $('table tr td').on('click', '.icon-save', function() {

        var that = $(this);
        var editable_inputs = that.parent().parent().find('td');

        editable_inputs.each(function() {
            if ($(this).is(':last-child') || ($(this).hasClass('not-editable'))) {

            } else {

                var new_value = $(this).find('input').val();
                $(this).html(new_value);

            }
        });

        that.parent().parent().find('td:last').html('<a href="#" class="icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a>');
        that.parent().parent().removeClass('uif-new-row'); // Not working for some reason

        /*
         * We also want to total the column, so we'll do that here.
         */

        // TODO: Total columns upon clicking 'save' icon

    });

});