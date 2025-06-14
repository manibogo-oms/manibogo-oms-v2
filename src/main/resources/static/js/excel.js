
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

function downloadExcelFile(name, rows, widths, headers) {

    const workbook = XLSX.utils.book_new();
    const worksheet = XLSX.utils.json_to_sheet(rows, { raw: false, header: headers });

    if (widths) {
        worksheet["!cols"] = widths.map(e => ({ 'wch': e }));
    }

    worksheet["!rows"] = [ 24, ...Array(rows.length).fill(36) ].map(e => ({ 'hpx': e }));

    for (let i in worksheet) {
        if (typeof(worksheet[i]) != "object") continue;
        let cell = XLSX.utils.decode_cell(i);

        const borderStyle = {
            style: "thin", color: "000000"
        };

        worksheet[i].s = {
            font: { name: 'arial', sz: '12' },
            alignment: {
                vertical: "center",
                wrapText: true,
            },
            border: {
                right: borderStyle,
                left: borderStyle,
                top: borderStyle,
                bottom: borderStyle,
            }
        };

        if (cell.r === 0) {
            worksheet[i].s.font['bold'] = true;
            worksheet[i].s.alignment['horizontal'] = 'center';
            worksheet[i].s.fill = {
                patternType: 'solid',
                fgColor: { rgb: 'C2FFC0' },
                bgColor: { rgb: 'C2FFC0' },
            };
        }
    }

    XLSX.utils.book_append_sheet(workbook, worksheet, "Sheet1");

    XLSX.writeFile(workbook, `${new Date().toLocaleDateString()} ${name}.xlsx`, { compression: true });
}

async function downloadPurchasedItemOrders(elm) {
    const productName = elm.dataset.productName;
    const response = await fetch(`/v2/purchase-order` + window.location.search);

    const rows = await response.json();
    downloadExcelFile(`${productName} 발주건`, nullSafe(rows),
        [ 6, 21, 12, 18, 12, 15, 15, 42, 30, 30, 30, 9, 9, 9, 60, 12, 12, 12, 12, 12, 15, 9, 15, 24, 42, 42, 9 ],
        [ "번호", "상품주문번호", "구매자명", "구매자연락처", "수취인명", "수취인연락처1", "수취인연락처2", "상품명", "옵션1", "옵션2", "옵션3", "합배송수", "수량", "지역", "주소", "발송기한", "희망배송일", "발주일", "출고일", "배송일", "배송방법", "배송비", "택배사", "송장번호", "고객메모", "발주메모", "상태" ]
    );

}

function nullSafe(rows) {
    return rows.map((e) => Object.fromEntries(Object.entries(e).map(([k, v]) => [k, v ? v : ''])));
}