
$(function() {

    $(".box").mouseover(function () {
      const idx = $(this).index();
      const boxW = $(this).width();
      const offsetL = (boxW * (idx - 1)); // 해당 인덱스 번호까지의 너비
        $(".inner-bar").addClass("on").stop().animate({'width':boxW, 'left': offsetL}, 230);
        //transition 효과추가?
      });

    $("nav").mouseout(function () {
        $(".inner-bar").removeClass("on");
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