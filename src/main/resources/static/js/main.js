document.addEventListener('DOMContentLoaded', function () {
    const themeToggleBtn = document.getElementById('themeToggleBtn');
    const lightThemeIcon = document.getElementById('lightThemeIcon');
    const darkThemeIcon = document.getElementById('darkThemeIcon');
    const body = document.body;

    const savedTheme = localStorage.getItem('theme');

    if (savedTheme) {
        body.classList.add(savedTheme);
        applyTheme(savedTheme);
    } else {
        body.classList.add('light-theme');
    }

    themeToggleBtn.addEventListener('click', function () {
        body.classList.toggle('dark-theme');
        body.classList.toggle('light-theme');

        const currentTheme = body.classList.contains('dark-theme') ? 'dark-theme' : 'light-theme';
        localStorage.setItem('theme', currentTheme);

        applyTheme(currentTheme);
        updateThemeIcon(currentTheme);
    });

    function applyTheme(theme) {

        if (theme === 'dark-theme') {
            body.classList.remove('light-theme');
            body.classList.add('dark-theme');
        } else {
            body.classList.remove('dark-theme');
            body.classList.add('light-theme');
        }

    }

    function updateThemeIcon(theme) {

        if (theme === 'light-theme') {
            lightThemeIcon.classList.remove('d-none');
            darkThemeIcon.classList.add('d-none');
        } else {
            lightThemeIcon.classList.add('d-none');
            darkThemeIcon.classList.remove('d-none');
        }
    }

    const currentTheme = body.classList.contains('dark-theme') ? 'dark-theme' : 'light-theme';
    updateThemeIcon(currentTheme);
});


function copyToClipboard(url) {
    const el = document.createElement('textarea');
    el.value = window.location.origin + url;
    document.body.appendChild(el);
    el.select();
    document.execCommand('copy');
    document.body.removeChild(el);
    alert('Link copied to clipboard!');
}


function clearPlaceholder(event) {
    const input = event.target;
    input.setAttribute('data-placeholder', input.getAttribute('placeholder'));
    input.removeAttribute('placeholder');
}

function restorePlaceholder(event) {
    const input = event.target;
    input.setAttribute('placeholder', input.getAttribute('data-placeholder'));
}


function togglePasswordVisibility() {
    const passwordInput = document.getElementById("password");

    if (passwordInput.type === "password") {
        passwordInput.type = "text";
        eyeIcon.classList.remove("fa-eye");
        eyeIcon.classList.add("fa-eye-slash");
        eyeIcon.classList.add("crossed");
    } else {
        passwordInput.type = "password";
        eyeIcon.classList.remove("fa-eye-slash");
        eyeIcon.classList.remove("crossed");
        eyeIcon.classList.add("fa-eye");
    }
}

const noteCards = document.querySelectorAll('.card');

noteCards.forEach(card => {
    card.addEventListener('mouseenter', () => {
        card.classList.add('hovered');
    });

    card.addEventListener('mouseleave', () => {
        card.classList.remove('hovered');
    });
});

function checkCardVisibility(card) {
    const rect = card.getBoundingClientRect();
    const halfHeight = rect.height / 2;
    const scrollTop = window.scrollY || document.documentElement.scrollTop;

    if (rect.top < halfHeight) {
        if (rect.top < halfHeight + 100) {
            window.scrollTo({ top: scrollTop + rect.top - halfHeight - 100, behavior: 'smooth' });
        }
    } else {
        const windowHeight = window.innerHeight;
        if (rect.bottom > windowHeight - halfHeight + 100) {
            window.scrollTo({ top: scrollTop + rect.bottom - windowHeight + halfHeight, behavior: 'smooth' });
        }
    }
}

function toggleView() {
    var cardsView = document.querySelector(".cards-view");
    var listView = document.querySelector(".list-view");
    if (cardsView.style.display !== "none") {
        cardsView.style.display = "none";
        listView.style.display = "block";
        localStorage.setItem("viewStyle", "list");

        var toggleIcon = document.getElementById("toggleIcon");
        toggleIcon.classList.remove("fa-list");
        toggleIcon.classList.add("fa-th");
    } else {
        cardsView.style.display = "block";
        listView.style.display = "none";
        localStorage.setItem("viewStyle", "cards");

        var toggleIcon = document.getElementById("toggleIcon");
        toggleIcon.classList.remove("fa-th");
        toggleIcon.classList.add("fa-list");
    }
}

window.onload = function () {
    var viewStyle = localStorage.getItem("viewStyle");
    if (viewStyle === "list") {
        var cardsView = document.querySelector(".cards-view");
        var listView = document.querySelector(".list-view");
        cardsView.style.display = "none";
        listView.style.display = "block";
    } else {
        var listView = document.querySelector(".list-view");
        listView.style.display = "none";
    }
};

document.getElementById("iconContainer").addEventListener("click", toggleView);







