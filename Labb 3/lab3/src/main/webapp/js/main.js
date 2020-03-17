$(document).ready(function() {
    $(document).on("click", "#loginbutton", function() {
    document.getElementById("popup").style.display = "block";});
    /*document.getElementById("popupdark").style.display = "block";*/
});

$(document).ready(function() {
    $(document).on("click", "#loginbutton2", function() {
    document.getElementById("popup").style.display = "none";});
    /*document.getElementById("popupdark").style.display = "block";*/
});

$('.carousel.carousel-multi-item.v-2 .carousel-item').each(function(){
  var next = $(this).next();
  if (!next.length) {
    next = $(this).siblings(':first');
  }
  next.children(':first-child').clone().appendTo($(this));

  for (var i=0;i<4;i++) {
    next=next.next();
    if (!next.length) {
      next=$(this).siblings(':first');
    }
    next.children(':first-child').clone().appendTo($(this));
  }
});