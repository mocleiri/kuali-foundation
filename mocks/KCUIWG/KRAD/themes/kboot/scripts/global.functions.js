/*
 * Javascript for POC prototype
 * clrux
 */


/*
 * Editable rows in datatables
 * Requires: table tr td
 */

$('table .uif-btn-edit').on('click', function() {

    alert('editing...');

    var that = $(this);
    var editable_inputs = that.parent().find('td').length - 1;

    editable_inputs.each(function() {
        if ($(this).hasClass('not-editable')) {

        } else {
            var current_value = $(this).text();
            $(this).html('<input type="text" name="asdf" id="asdf" value="' + current_value + '"');
            $(this).parent().addClass('uif-row-edit');
        }
    });

    that.removeClass('icon-save').addClass('icon-edit uif-btn-edit');

});

$('table tr td .icon-save').on('click', function() {

    alert('saving...');

});