
document.getElementById('btnSearchAddress')
    .addEventListener('click', () => {
    new daum.Postcode({
        oncomplete: function(data) {
            console.log(data);
            document.getElementById('recipientAddress1').value = data.address;
            document.getElementById('recipientZipCode').value = data.zonecode;
        }
    }).open();

});

function recipientSamesAsCustomer(e) {
    const recipientName = document.getElementById('recipientName');
    const recipientTel1 = document.getElementById('recipientTel1');
    const recipientTel2 = document.getElementById('recipientTel2');

    if (!e.checked) {
        recipientName.removeAttribute('disabled');
        recipientTel1.removeAttribute('disabled');
        recipientTel2.removeAttribute('disabled');
        return;
    }

    recipientName.setAttribute('disabled', 'true');
    recipientTel1.setAttribute('disabled', 'true');
    recipientTel2.setAttribute('disabled', 'true');
}

document.getElementById('isRecipientSameAsCustomer')
    .addEventListener('change', (e) => recipientSamesAsCustomer(e.target));

document.addEventListener('DOMContentLoaded', () => {
    recipientSamesAsCustomer(document.getElementById('isRecipientSameAsCustomer'))
})