$(document).ready(function () {
    $('.delete').on('click', function () {
        $('.modal').modal('show')
        var id = $(this).parent().find('.subject_id').val();
        var me = $(this);
        $('#modal_delete').on('click',function(){
            $.ajax({
                type: "GET",
                url: "http://localhost:8080/admin/subjects/delete/" + id,
                // data: "data",
                // dataType: "dataType",
                success: function (response) {
                    var tr = me.parent().parent()
                    tr.remove();
                },
                error: function (errr){
                    console.log('Error');
                }
            });
        })
    });
});