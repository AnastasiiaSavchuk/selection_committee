//плавна заміна фото кожні 5 секунд
let slideNextIndex = 0;
showNextSlide();

function showNextSlide() {
    let i;
    let slides = document.getElementsByClassName("start-photo");
    let dots = document.getElementsByClassName("dot");
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    slideNextIndex++;
    if (slideNextIndex > slides.length) {
        slideNextIndex = 1
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" activeDot", "");
    }
    slides[slideNextIndex - 1].style.display = "block";
    dots[slideNextIndex - 1].className += " activeDot";
    setTimeout(showNextSlide, 6000); // Change image every 8 seconds
}

//зміна слайда вибором кнопки

showSlide(slideNextIndex);

function currentSlide(n) {
    showSlide(slideNextIndex = n);
}

function showSlide(n) {
    var i;
    var slides = document.getElementsByClassName("start-photo");
    var dots = document.getElementsByClassName("dot");
    if (n > slides.length) {
        slideNextIndex = 1
    }
    if (n < 1) {
        slideNextIndex = slides.length
    }
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" activeDot", "");
    }
    slides[slideNextIndex - 1].style.display = "block";
    dots[slideNextIndex - 1].className += " activeDot";
}