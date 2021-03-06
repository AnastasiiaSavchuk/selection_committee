//initial language set
function setLanguage(clickedObj) {
    let englishLink = document.getElementById('english-id');
    let ukrainianLink = document.getElementById('ukrainian-id');
    let selectedLang = clickedObj.innerHTML;

    let langsArr = ['EN', 'UK'];
    let linkIndex = langsArr.indexOf(englishLink.innerText);
    if (linkIndex > -1) {
        langsArr.splice(linkIndex, 1);
    }

    linkIndex = langsArr.indexOf(ukrainianLink.innerText);
    if (linkIndex > -1) {
        langsArr.splice(linkIndex, 1);
    }
    clickedObj.innerHTML = langsArr[0];

    let setLangLink = document.createElement('a');
    setLangLink.href = '${pageContext.request.contextPath}/controller?command=setLanguage&language=' + convertLang(selectedLang);
    setLangLink.click();
    location.reload();
}

function convertLang(langInnerHtml) {
    if (langInnerHtml == null) return 'en';
    if (langInnerHtml === 'EN') return 'en';
    if (langInnerHtml === 'UK') return 'uk';
}

// Mobile Navigation
if ($('#nav-menu-container').length) {
    var $mobile_nav = $('#nav-menu-container').clone().prop({
        id: 'mobile-nav'
    });
    $mobile_nav.find('> ul').attr({
        'class': '',
        'id': ''
    });
    $('body').append($mobile_nav);
    $('body').prepend('<button type="button" id="mobile-nav-toggle"><i class="fa fa-bars"></i></button>');
    $('body').append('<div id="mobile-body-overly"></div>');
    $('#mobile-nav').find('.menu-has-children').prepend('<i class="fa fa-chevron-down"></i>');

    $(document).on('click', '.menu-has-children i', function (e) {
        $(this).next().toggleClass('menu-item-active');
        $(this).nextAll('ul').eq(0).slideToggle();
        $(this).toggleClass("fa-chevron-up fa-chevron-down");
    });

    $(document).on('click', '#mobile-nav-toggle', function (e) {
        $('body').toggleClass('mobile-nav-active');
        $('#mobile-nav-toggle i').toggleClass('fa-times fa-bars');
        $('#mobile-body-overly').toggle();
    });

    $(document).click(function (e) {
        var container = $("#mobile-nav, #mobile-nav-toggle");
        if (!container.is(e.target) && container.has(e.target).length === 0) {
            if ($('body').hasClass('mobile-nav-active')) {
                $('body').removeClass('mobile-nav-active');
                $('#mobile-nav-toggle i').toggleClass('fa-times fa-bars');
                $('#mobile-body-overly').fadeOut();
            }
        }
    });
} else if ($("#mobile-nav, #mobile-nav-toggle").length) {
    $("#mobile-nav, #mobile-nav-toggle").hide();
}

// Smooth scroll for the menu and links with .scrollto classes
$('.nav-menu a, #mobile-nav a, .scrollto').on('click', function () {
    if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
        var target = $(this.hash);
        if (target.length) {
            var top_space = 0;

            if ($('#header').length) {
                top_space = $('#header').outerHeight();

                if (!$('#header').hasClass('header-fixed')) {
                    top_space = top_space - 20;
                }
            }

            $('html, body').animate({
                scrollTop: target.offset().top - top_space
            }, 1500, 'easeInOutExpo');

            if ($(this).parents('.nav-menu').length) {
                $('.nav-menu .menu-active').removeClass('menu-active');
                $(this).closest('li').addClass('menu-active');
            }

            if ($('body').hasClass('mobile-nav-active')) {
                $('body').removeClass('mobile-nav-active');
                $('#mobile-nav-toggle i').toggleClass('fa-times fa-bars');
                $('#mobile-body-overly').fadeOut();
            }
            return false;
        }
    }
});

//pagination
$(document).ready(function () {
    $('#pagination').after('<div id="nav"></div>');
    let rowsShown = 7;
    let rowsTotal = $('#pagination tbody tr').length;
    let numPages = rowsTotal / rowsShown;
    if (rowsTotal > rowsShown) {
        for (let i = 0; i < numPages; i++) {
            let pageNum = i + 1;
            $('#nav').append('<a href="#" rel="' + i + '">' + pageNum + '</a>');
        }
        $('#pagination thead tr').show();
        $('#pagination tbody tr').hide();
        $('#pagination tbody tr').slice(0, rowsShown).show();
        $('#nav a:first').addClass('active');
        $('#nav a').bind('click', function () {
            $('#nav a').removeClass('active');
            $(this).addClass('active');
            let currPage = $(this).attr('rel');
            let startItem = currPage * rowsShown;
            let endItem = startItem + rowsShown;
            $('#pagination tbody tr').css('opacity', '0.0').hide()
                .slice(startItem, endItem).css('display', 'table-row').animate({opacity: 1}, 300);
        });
    } else {
        $('#pagination thead tr').show();
        $('#pagination tbody tr').show();
    }
});