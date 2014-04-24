/*
 * Javascript for POC prototype
 * clrux, thrclark
 */

jQuery(document).ready(function($) {

    /*
     * Editable rows in datatables
     * Requires: table tr td
     *
     * Notes: 1) To prevent editing of a field, add .not-editable to the parent <td>
     *        2) Requires the icons .icon-save and .icon-edit
     *
     * Original: clrux
     *
     * TODO:  1) Add column totaling
     * TODO:  2) Get row highlighting for editing
     */


    $('table').on('click keydown', '.icon-edit', function(e) {

        e.preventDefault();

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

                // If the data-edit-type is a date...
                } else if ($(this).find('span').data('edit-type') == 'date') {
                    var current_value = $(this).text();
                    $(this).html('<input type="date" class="form-control input-sm" name="' + $(this).find('span').data('edit-name') + '" id="' + $(this).find('span').data('edit-id') + '" value="' + current_value + '" size="3" />');

                // If the data-edit-type is a select...
                } else if ($(this).find('span').data('edit-type') == 'select') {

                    var o_data = $(this).find('span');
                    var o_data_options = o_data.data('edit-type-options');
                    var o_data_options_array = o_data_options.split(',');
                    var current_value = $(this).text();
                    var rebuild_select_menu = '<select class="form-control input-sm" name="' + $(this).find('span').data('edit-name') + '" id="' + $(this).find('span').data('edit-id') + '"><option value="' + current_value + '" selected="selected">' + current_value + '</option>';

                    for (var i = 0; i < o_data_options_array.length; i++) {

                        if (o_data_options_array[i] == current_value) {

                        } else {
                            rebuild_select_menu += '<option value="' + o_data_options_array[i] + '">' + o_data_options_array[i] + '</option>';
                        }
                    }

                    rebuild_select_menu += '</select>';

                    $(this).html(rebuild_select_menu);

                // If the data-edit-type is a textarea...
                } else if ($(this).find('span').data('edit-type') == 'textarea') {
                    var current_value = $(this).text();
                    $(this).html('<textarea class="form-control input-sm" name="' + $(this).find('span').data('edit-name') + '" id="' + $(this).find('span').data('edit-id') + '">' + current_value + '</textarea>');

                // If it's something else, skip it for now
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

        e.preventDefault();

        if (e.keyCode == 13) {
            $(this).trigger('click');
        }

        $(this).parent().parent().after(decodeURI($(this).data('cancel-object')));
        $(this).parent().parent().prev().remove();

        // Removes $this row
        $(this).parent().parent().remove();

        return false;

    }).on('click keydown', '.uif-delete', function(e) {

        e.preventDefault();

        $(this).parent().parent().prev().fadeOut();
        $(this).parent().parent().fadeOut();

    }).on('click keydown', '.icon-save', function(e) {

        e.preventDefault();

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

                // If it's a date...
                } else if ($(this).find('input').length > 0) {
                    var new_value = '<span data-edit-type="date" data-edit-name="" data-edit-id="">' + $(this).find('input').val() + '</span>';
                    $(this).html(new_value);

                // If it's a select menu...
                } else if ($(this).find('select').length > 0) {

                    var that = $(this);
                    var edit_type_options = [];

                    $(that.find('select>option')).each(function() {
                        edit_type_options.push($(this).text());
                    });

                    var new_value = '<span data-edit-type="select" data-edit-type-options="' + edit_type_options + '" data-edit-name="" data-edit-id="">' + that.find('select option:selected').text() + '</span>';
                    that.html(new_value);

                // If it's a textarea...
                } else if ($(this).find('textarea').length > 0) {
                    var new_value = '<span data-edit-type="textarea" data-edit-name="" data-edit-id="">' + $(this).find('textarea').val() + '</span>';
                    $(this).html(new_value);

                // If it's something else, skip it at this time
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
     *
     * Original: clrux
     */

    setupSidebarNavMenu('uif_budget_navigation_menu', 'icon-angle-down', 'icon-angle-right');


    /*
     * Select listener
     * This is specifically for the personnel addition modal
     *
     * Original: thrclark
     * Update: clrux
     */


    $('select#personType').on('change', function() {

        if ( $(this).filter(':selected') == 'emp' || $(this).val() == 'emp' ) {
            $('.employee').show();
        } else {
            $('.employee').hide();
        }
		
  		if ( $(this).filter(':selected') == 'emp1' || $(this).val() == 'emp1' ) {
            $('.employee1').show();
        } else {
            $('.employee1').hide();
        }

        if ( $(this).filter(':selected') == 'nonemp' || $(this).val() == 'nonemp' ) {
            $('.nonemployee').show();
        } else {
            $('.nonemployee').hide();
        }

        if ( $(this).filter(':selected') == 'tbn' || $(this).val() == 'tbn' ) {
            $('.tbn').show();
        } else {
            $('.tbn').hide();
        }

    });



    /*
     * Cookie listener
     * Listens for 'set-cookie' class click and sets cookie with type and value
     *
     * clrux
     */

     $('.set-cookie').on('click', function(e) {

         // e.preventDefault();

         cookie_type = $(this).data('cookie-type');
         cookie_value = $(this).data('cookie-value');
         cookie_time = Math.floor((new Date()).getTime() / 1000);

         document.cookie = cookie_type + "=" + cookie_value + "; expires=" + cookie_time + "; path=/";

     });


});

