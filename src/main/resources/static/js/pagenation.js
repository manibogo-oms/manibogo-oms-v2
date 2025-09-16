
document.getElementById('selectPageSize')
.addEventListener('change', (e) => {
    const link = document.getElementById('btnChangePageSize');
    const href = e.target.value;

    link.setAttribute('href', href);
})