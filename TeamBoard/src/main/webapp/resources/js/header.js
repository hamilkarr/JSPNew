
$(function() {
 

    $(".box").mouseover(function () {
        $(".nav_over").after("<div class='border_bottom'></div>")
        $(this).children(".border_bottom").append("<div class='border_bottom_each'></div>")
    });

    $(".box").mouseout(function () {
        $("div").remove(".border_bottom");
    });

    $(".show-bar").hide();
    $("nav").mouseover(function () {
        $(".show-bar").show();

    });
    $("nav").mouseout(function () {
        $(".show-bar").hide();
    });

        const navOffset = $( 'nav' ).offset();
        $( window ).scroll( function() {
          if ( $( window ).scrollTop() > navOffset.top ) {
            $( 'nav' ).addClass( 'fixed-nav' );
          }
          else {
            $( 'nav' ).removeClass( 'fixed-nav' );
          }
        });
        
});