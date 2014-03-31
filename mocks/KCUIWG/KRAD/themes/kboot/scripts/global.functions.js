/*
 * Javascript for POC prototype
 * clrux
 */

jQuery(document).ready(function($) {

    /*
     * Editable rows in datatables
     * Requires: table tr td
     * Notes: 1) To prevent editing of a field, add .not-editable to the parent <td>
     *        2) Requires the icons .icon-save and .icon-edit
     * TODO:  1) Add column totaling
     *        2) Get row highlighting for editing
     */

    $('table tr td').on('click keydown', '.icon-save', function(e) {

        if(e.keyCode == 13) {
            $(this).trigger('click');
        }

        var that = $(this);
        var editable_inputs = that.parent().parent().find('td');

        editable_inputs.each(function() {
            if ($(this).is(':last-child') || ($(this).hasClass('not-editable'))) {

            } else {

                // If it's an input...
                if ($(this).find('input').length > 0) {
                    var new_value = '<span data-edit-type="input" data-edit-name="" data-edit-id="">' + $(this).find('input').val() + '</span>';
                    $(this).html(new_value);

                // If it's a select menu...
                } else if ($(this).find('select').length > 0) {
                    var new_value = '<span data-edit-type="select" data-edit-name="" data-edit-id="">' + $(this).find('select option:selected').text() + '</span>';
                    $(this).html(new_value);

                // If it's a textarea...
                } else if ($(this).find('textarea').length > 0) {
                    var new_value = '<span data-edit-type="textarea" data-edit-name="" data-edit-id="">' + $(this).find('textarea').val() + '</span>';
                    $(this).html(new_value);

                // If it's something else...
                // Just skip it at this time
                } else {

                }

            }
        });

        that.parent().parent().find('td:last').html('<a tabindex="0" href="#" class="icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a>');
        that.parent().parent().removeClass('uif-new-row'); // Not working for some reason

        /*
         * We also want to total the column, so we'll do that here.
         */

        // TODO: Total columns upon clicking 'save' icon

    });

    $('table tr td').on('click keydown', '.icon-edit', function(e) {

        if(e.keyCode == 13) {
            $(this).trigger('click');
        }

        var that = $(this);
        var editable_inputs = that.parent().parent().find('td');

        editable_inputs.each(function() {
            if ($(this).is(':last-child') || ($(this).hasClass('not-editable'))) {

            } else {

                // If the data-edit-type is an input...
                if ($(this).find('span').data('edit-type') == 'input') {
                    var current_value = $(this).text();
                    $(this).html('<input type="text" class="form-control input-sm" name="' + $(this).find('span').data('edit-name') + '" id="' + $(this).find('span').data('edit-id') + '" value="' + current_value + '" size="3" />');

                // If the data-edit-type is a select...
                } else if ($(this).find('span').data('edit-type') == 'select') {
                    var current_value = $(this).text();
                    $(this).html('<select class="form-control input-sm" name="' + $(this).find('span').data('edit-name') + '" id="' + $(this).find('span').data('edit-id') + '"><option value="' + current_value + '">' + current_value + '</option>');

                // If the data-edit-type is a textarea...
                } else if ($(this).find('span').data('edit-type') == 'textarea') {
                    var current_value = $(this).text();
                    $(this).html('<textarea class="form-control input-sm" name="' + $(this).find('span').data('edit-name') + '" id="' + $(this).find('span').data('edit-id') + '">' + current_value + '</textarea>');

                // If it's something else
                // Skip it for now
                } else {

                }

            }
        });

        that.parent().parent().find('td:last').html('<a tabindex="0" class="icon-save" href="#"><span class="sr-only">Save</span></a>');
        that.parent().parent().addClass('uif-new-row'); // Not working for some reason

    });

});