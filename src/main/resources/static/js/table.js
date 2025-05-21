for (const element of document.getElementsByClassName('chk-select-all-rows')) {
    element.addEventListener('change', (e) => {
        const checkBoxes = document.getElementsByClassName('chk-select-row');

        for (const checkBox of checkBoxes) {
            checkBox.checked = e.target.checked;
            checkBox.dispatchEvent(new Event('change'));
        }
    });
}

for (const checkbox of document.getElementsByClassName('chk-select-row')) {
    checkbox.addEventListener('change', (_) => {
        if (checkbox.checked === false) {
            checkbox.dispatchEvent(new Event('unselected', { bubbles : true }));
            return;
        }
        checkbox.dispatchEvent(new Event('selected', { bubbles : true }));
    });
}

for (const tableRow of document.getElementsByClassName('table-row')) {
    tableRow.addEventListener('selected', (e) => {
        e.stopPropagation();
        tableRow.classList.add('selected');
    });

    tableRow.addEventListener('unselected', (e) => {
        e.stopPropagation();

        if (!tableRow.classList.contains('selected')) return;
        tableRow.classList.remove('selected');
    });
}