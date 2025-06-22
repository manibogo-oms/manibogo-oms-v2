
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