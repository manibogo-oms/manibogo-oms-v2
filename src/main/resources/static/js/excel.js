
function parseOrder(order) {
    return {
        'orderNumber': order['주문번호'],
        'customerName': order['구매자명'],
        'customerPhoneNumber': order['구매자연락처'],
        'customerMessage': order['배송메세지'],
        'salesChannel': '스마트스토어',
        'recipientName': order['수취인명'],
        'recipientPhoneNumber1': order['수취인연락처1'],
        'recipientPhoneNumber2': order['수취인연락처2'],
        'recipientAddress1': order['기본배송지'],
        'recipientAddress2': order['상세배송지'],
        'recipientAddressZipcode': order['우편번호'],
        'itemOrderNumber': order['상품주문번호'],
        'productNumber': order['판매자 상품코드'],
        'productName': order['상품명'],
        'optionInfo': order['옵션정보'],
        'amount': order['수량'],
        'totalPrice': order['정산예정금액'],
        'shippingMethod': order['배송방법'],
        'shippingChargeType': order['배송비 형태'],
        'itemOrderPlacedAt': order['주문일시'],
        'dispatchDeadline': order['발송기한'],
    };
}

document
    .getElementById('uploadSmartStoreItemOrder')
    .addEventListener('change', async (e) => {
        const orders = await handleExcelFile(e.target.files[0], 1);

        const input = document.getElementById('iptExternalOrders');

        input.value = JSON.stringify(orders.map(parseOrder));

    });

function handleExcelFile(file, startRow) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();

        reader.onload = () => {
            const arrayBuffer = reader.result;
            const workbook = XLSX.read(arrayBuffer, { type:'binary',cellText: false,cellDates:true });

            const sheet = workbook.Sheets[workbook.SheetNames[0]];

            const json = XLSX.utils.sheet_to_json(sheet, { range: startRow });
            resolve(json);
        };

        reader.onerror = () => {
            reject('파일을 읽는 중 에러가 발생했습니다.');
        }

        reader.readAsArrayBuffer(file);
    });
}