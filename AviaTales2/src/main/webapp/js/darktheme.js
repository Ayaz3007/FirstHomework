function darkTheme() {
    let theme = document.getElementById('change-theme').value;

    if(localStorage.getItem('theme') === null || localStorage.getItem('theme') === 'light') {
        document.body.classList.add("bg-dark", "text-white");
        localStorage.setItem('theme', 'dark');
    }
    else if(localStorage.getItem('theme') === 'dark') {
        document.body.classList.remove("bg-dark",  "text-white");
        localStorage.setItem('theme', 'light');
    }
}
function loadTheme() {
    if(localStorage.getItem('theme') === null || localStorage.getItem('theme') === 'light') {
        document.body.classList.remove("bg-dark", "text-white");
    }
    else if(localStorage.getItem('theme') === 'dark') {
        document.body.classList.add("bg-dark",  "text-white");
    }
}

window.onload = loadTheme;