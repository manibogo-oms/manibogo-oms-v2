
function parseOrder(order) {
    return {
        'orderNumber': order['상품주문번호'],
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
        'productNumber': order['판매자 상품코드'],
        'productName': order['상품명'],
        'optionInfo': order['옵션정보'],
        'amount': order['수량'],
        'totalPrice': order['정산예정금액'],
        'shippingMethod': order['배송방법'],
        'shippingChargeType': order['배송비 형태'],
        'orderPlacedAt': order['주문일시'],
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

function parseParcel(parcel) {

    if (!parcel['번호']) return null;

    return {
        'itemOrderNumber': parcel['주문번호'],
        'shippingTrackingNumber': parcel['운송장번호'],
        'shippingCompanyName': '로젠택배'
    };
}

document.getElementById('uploadParcel')
    .addEventListener('change', async (e) => {
        const rows = await handleExcelFile(e.target.files[0], 1);

        document.getElementById('iptParcel').value =
            JSON.stringify(rows.map(parseParcel).filter(row => row != null));
    })

function parseItemOrderState(itemOrderState) {
    return {
        'itemOrderNumber': itemOrderState['상품주문번호'],
        'targetState': itemOrderState['주문상태'],
        'changedAt': itemOrderState['구매확정일'] || itemOrderState['취소승인일']
    };
}

document.getElementById('uploadItemOrderState')
    .addEventListener('change', async (e) => {
        const rows = await handleExcelFile(e.target.files[0], 0);

        console.log(rows);

        document.getElementById('iptItemOrderState').value =
            JSON.stringify(rows.map(parseItemOrderState).filter(row => row != null));
    })

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

const headerWidth = {
    "번호": 6,
    "상품주문번호": 21,
    "구매자명": 12,
    "구매자연락처": 18,
    "수취인명": 12,
    "수취인연락처1": 15,
    "수취인연락처2": 15,
    "상품명": 42,
    "옵션1": 30,
    "옵션2": 30,
    "옵션3": 30,
    "합배송수": 9,
    "수량": 9,
    "지역": 9,
    "주소": 60,
    "발송기한": 12,
    "희망배송일": 12,
    "발주일": 12,
    "출고일": 12,
    "배송일": 12,
    "배송방법": 15,
    "배송비": 9,
    "택배사": 15,
    "송장번호": 24,
    "고객메모": 42,
    "발주메모": 42,
    "상태": 9
};

async function downloadPurchasedItemOrders(elm) {
    const productName = elm.dataset.productName;
    const response = await fetch(`/v2/purchase-order` + window.location.search);

    const rows = await response.json();
    const headers = [ "번호", "상품주문번호", "구매자명", "구매자연락처", "수취인명", "수취인연락처1", "수취인연락처2", "상품명", "옵션1", "옵션2", "옵션3", "합배송수", "수량", "지역", "주소", "발송기한", "희망배송일", "발주일", "출고일", "배송일", "배송방법", "배송비", "택배사", "송장번호", "고객메모", "발주메모", "상태" ];
    downloadExcelFile(`${productName} 발주건`, nullSafe(rows),
        headers.map(e => headerWidth[e]),
        headers
    );
}

async function downloadSmartStoreParcelList(elm) {
    const response = await fetch(`/v2/smart-store-parcel` + window.location.search);

    const rows = await response.json();
    const headers = ['상품주문번호', '배송방법', '택배사', '송장번호',  "구매자명", "구매자연락처", ];
    downloadExcelFile(`발송처리`, nullSafe(rows),
        headers.map(e => headerWidth[e]),
        headers
    );
}



function nullSafe(rows) {
    return rows.map((e) => Object.fromEntries(Object.entries(e).map(([k, v]) => [k, v ? v : ''])));
}