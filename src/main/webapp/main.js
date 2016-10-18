/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Document Ready
$(function(){

    checkBoxes();
    
    $('.checkbox').click(function(){

        checkBoxes();
    });
    
    $('#clear').click(function(){
        $('.checkbox').prop("checked", false);
        checkBoxes();
    })
    
    function checkBoxes(){
        var checked = $("#form1 input:checked").length > 0;
        if (checked){
            $('#btnAdd').prop('disabled', true);
            $('#btnDelete').prop('disabled', false);
            $('#btnUpdate').prop('disabled', false);
            $('#clear').prop('disabled', false);
        } else {
            $('#btnAdd').prop('disabled', false);
            $('#btnDelete').prop('disabled', true);
            $('#btnUpdate').prop('disabled', true);
            $('#clear').prop('disabled', true)
        }
    }
});
