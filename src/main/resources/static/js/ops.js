$(function(){
    //$('#toacode2').hide();

    $('#dep,#drw').on("click",function (){
        $('#toacode2').hide();
    });

    $('#trf').on("click",function (){
        $('#toacode2').show();
    });

});