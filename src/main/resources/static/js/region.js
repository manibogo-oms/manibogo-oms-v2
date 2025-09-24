async function initSido() {
    const sidoElm = document.getElementById('sido');
    if (!sidoElm) return;

    const response = await fetch(`/v2/regions/options?level=0&selected=${sidoElm.getAttribute('value') ?? ''}`);
    sidoElm.innerHTML = '<option selected="selected" value="">시/도 선택</option>' + await response.text();

}

async function initSigungu(sido) {
    const sigunguElm = document.getElementById('sigungu');
    if (!sido || !sigunguElm) return;

    const response = await fetch(`/v2/regions/options?level=1&parent=${sido}&selected=${sigunguElm.getAttribute('value') ?? ''}`);
    sigunguElm.innerHTML = '<option selected="selected" value="">시/군/구 선택</option>' + await response.text();
}

document.addEventListener('DOMContentLoaded', () => {
    const sidoElm = document.getElementById('sido');

    initSido();
    initSigungu(sidoElm.getAttribute('value'));
    sidoElm.addEventListener('change', (e) => { initSigungu(sidoElm.value); });
});