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


    $('table').on('click keydown', '.icon-edit', function(e) {

        if (e.keyCode == 13) {
            $(this).trigger('click');
        }

        var that = $(this);
        var row_before_edit = encodeURI('<tr>' + that.parent().parent().html() + '</tr>');
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
                    var current_value = $(this).text();
                    $(this).html('<input type="text" class="form-control input-sm" name="' + $(this).find('span').data('edit-name') + '" id="' + $(this).find('span').data('edit-id') + '" value="' + current_value + '" size="3" />');
                }

            }
        });

        if (that.parent().parent().next().hasClass('uif-edit-append-row')) {

        } else {

            var uif_action_row = '<tr class="uif-new-row uif-edit-append-row"><td colspan="' + editable_inputs.length + '">';

            if (that.parent().parent().hasClass('not-deletable')) {

                uif_action_row += '<a href="#" class="uif-cancel pull-right" data-cancel-object="' + row_before_edit + '">Cancel edit</a>';

            } else {

                uif_action_row += '<a href="#" class="uif-delete pull-left danger"><span class="icon icon-trash"></span> Delete entry</a><a href="#" class="uif-cancel pull-right" data-cancel-object="' + row_before_edit + '">Cancel edit</a>';

            }

            uif_action_row +=  '</td></tr>';

            that.parent().parent().after(uif_action_row);
        }

        that.parent().parent().find('td:last').html('<a tabindex="0" class="icon-save" href="#" data-cancel-object="' + row_before_edit +'"><span class="sr-only">Save</span></a>');
        that.parent().parent().addClass('uif-new-row'); // Not working for some reason

    }).on('click keydown', '.uif-cancel', function(e) {

        if (e.keyCode == 13) {
            $(this).trigger('click');
        }

        $(this).parent().parent().after(decodeURI($(this).data('cancel-object')));
        $(this).parent().parent().prev().remove();

        // Removes $this row
        $(this).parent().parent().remove();

        return false;

    }).on('click keydown', '.uif-delete', function(e) {

        $(this).parent().parent().prev().fadeOut();
        $(this).parent().parent().fadeOut();

    }).on('click keydown', '.icon-save', function(e) {

        if (e.keyCode == 13) {
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

        // Remove cancel row beneath
        if (that.parent().parent().next().hasClass('uif-edit-append-row')) {
            that.parent().parent().next().remove();
        } else {

        }

        that.parent().parent().find('td:last').html('<a tabindex="0" href="#" class="icon-edit uif-btn-edit"><span class="sr-only">Edit</span></a>');
        that.parent().parent().removeClass('uif-new-row'); // Not working for some reason

        /*
         * We also want to total the column, so we'll do that here.
         */

        // TODO: Total columns upon clicking 'save' icon

    });


    /*
     * Sidebar collapse and expand
     * Requires: krad.variables.js
     *           krad.widgets.js
     *           (and jQuery, of course)
     */

    setupSidebarNavMenu('uif_budget_navigation_menu', 'icon-angle-down', 'icon-angle-right');

});