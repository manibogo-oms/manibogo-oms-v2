function openPopup(url, width, height) {

    const left=
        Math.round(window.screenX + (window.outerWidth / 2) - (width / 2));

    const top=
        Math.round(window.screenY + (window.outerHeight / 2) - (height / 2));

    return window.open(url, document.title,
        `width=${width},height=${height},left=${left},top=${top}`);
}