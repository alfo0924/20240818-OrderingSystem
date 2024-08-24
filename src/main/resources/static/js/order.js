const shopitems = [
    {
        id: "shop1",
        information: [
            { img: "shop1.jpg", name: "超認真蔬果店" }
        ],
        products: [
            { img: "shop1_1.jpg", name: "檸檬", price: 50 },
            { img: "shop1_2.jpg", name: "青蘋果", price: 60 },
            { img: "shop1_3.jpg", name: "萊姆", price: 40 },
            { img: "shop1_4.jpg", name: "甜菜", price: 70 }
        ]
    },
    {
        id: "shop2",
        information: [
            { img: "shop2.jpg", name: "椅子咖啡店" }
        ],
        products: [
            { img: "shop2_1.jpg", name: "拿鐵咖啡", price: 120 },
            { img: "shop2_2.jpg", name: "奶油覆盆子鬆餅", price: 150 },
            { img: "shop2_3.jpg", name: "抹茶拿鐵", price: 110 },
            { img: "shop2_4.jpg", name: "焦糖瑪奇朵", price: 130 },
            { img: "shop2_5.jpg", name: "藍莓鬆餅", price: 140 },
            { img: "shop2_6.jpg", name: "美式咖啡", price: 90 }
        ]
    },
    {
        id: "shop3",
        information: [
            { img: "shop3.jpg", name: "霹頭五披薩店" }
        ],
        products: [
            { img: "shop3_1.jpg", name: "薩拉米香腸披薩", price: 120 },
            { img: "shop3_2.jpg", name: "伊比利火腿披薩", price: 150 },
            { img: "shop3_3.jpg", name: "瑪格麗特披薩", price: 110 },
            { img: "shop3_4.jpg", name: "方形培根披薩", price: 130 },
            { img: "shop3_5.jpg", name: "莫札瑞拉火腿披薩", price: 140 },
            { img: "shop3_6.jpg", name: "薄皮起司披薩", price: 90 }
        ]
    },
    {
        id: "shop4",
        information: [
            { img: "shop4.jpg", name: "馬東石肉店" }
        ],
        products: [
            { img: "shop4_1.jpg", name: "牛魚雞三重奏", price: 540 },
            { img: "shop4_2.jpg", name: "牛肩肉", price: 450 },
            { img: "shop4_3.jpg", name: "A5和牛切片", price: 890 },
            { img: "shop4_4.jpg", name: "放山土雞雞胸肉", price: 125 },
            { img: "shop4_5.jpg", name: "切塊牛肋條", price: 360 },
            { img: "shop4_6.jpg", name: "老饕牛排組合", price: 999 }
        ]
    },
    {
        id: "shop5",
        information: [
            { img: "shop5.jpg", name: "青筍筍蔬果行" }
        ],
        products: [
            { img: "shop5_1.jpg", name: "五穀雜糧", price: 30 },
            { img: "shop5_2.jpg", name: "藍莓", price: 75 },
            { img: "shop5_3.jpg", name: "南瓜", price: 65 },
            { img: "shop5_4.jpg", name: "拉拉山水蜜桃", price: 220 },
            { img: "shop5_5.jpg", name: "青森蘋果", price: 210 }

        ]
    },
    {
        id: "shop6",
        information: [
            { img: "shop6.jpg", name: "BarBarDrink" }
        ],
        products: [
            { img: "shop6_1.jpg", name: "愛爾蘭咖啡", price: 350 },
            { img: "shop6_2.jpg", name: "嫣紅佳人", price: 320 },
            { img: "shop6_3.jpg", name: "四劍客", price: 888 },
            { img: "shop6_4.jpg", name: "蘇格蘭紅茶", price: 170 },
            { img: "shop6_5.jpg", name: "西西里檸檬", price: 180 },
            { img: "shop6_6.jpg", name: "大摩15年", price: 400 },
            { img: "shop6_7.jpg", name: "心痛的感覺", price: 200 },

        ]
    },
];

// 將購物車資料存入 localStorage
function addToCart(productName, productPrice, quantity) {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    const productIndex = cart.findIndex(item => item.name === productName);

    if (productIndex > -1) {
        // 如果產品已存在於購物車中，則更新數量
        cart[productIndex].quantity += parseInt(quantity);
    } else {
        // 否則將新產品加入購物車
        cart.push({
            name: productName,
            price: productPrice,
            quantity: parseInt(quantity)
        });
    }

// 更新購物車到 localStorage
    localStorage.setItem('cart', JSON.stringify(cart));
    alert('已加入購物車！');
}

// 渲染店家列表
function renderStoreList() {
    const storeList = document.getElementById('store-list');
    shopitems.forEach(store => {
        const storeCard = `
            <div class="col-md-4">
                <div class="card">
                    <img src="/imgs/${store.information[0].img}" class="card-img-top" alt="${store.information[0].name}">
                    <div class="card-body">
                        <h5 class="card-title">${store.information[0].name}</h5>
                        <a href="/orders/list?shopId=${store.id}" class="btn btn-primary">View Products</a>
                    </div>
                </div>
            </div>
        `;
        storeList.innerHTML += storeCard;
    });
}

// 渲染產品列表
function renderProductList() {
    // 取得URL中的shopId參數
    const urlParams = new URLSearchParams(window.location.search);
    const shopId = urlParams.get('shopId');

    // 根據shopId查找相應的店家資料
    const shop = shopitems.find(store => store.id === shopId);

    if (shop) {
        document.getElementById('store-name').innerText = shop.information[0].name;

        const productList = document.getElementById('product-list');
        shop.products.forEach(product => {
            const productCard = `
                <div class="col-md-4">
                    <div class="card">
                        <img src="/imgs/${product.img}" class="card-img-top" alt="${product.name}">
                        <div class="card-body">
                            <span class="card-title">${product.name}</span>
                            <span class="card-text">$${product.price}</span>
                            <div class="flex-container">
                                <button class="btn btn-success" onclick="addToCart('${product.name}', ${product.price}, document.getElementById('quantity-${product.name}').value)">Add to Cart</button>
                                <select class="form-select inline-select" id="quantity-${product.name}">
                                    ${Array.from({length: 10}, (_, i) => `<option value="${i + 1}">${i + 1}</option>`).join('')}
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            `;
            productList.innerHTML += productCard;
        });
    } else {
        document.getElementById('store-name').innerText = "店家不存在";
    }
}
// 渲染購物車內容
function renderCart() {
    const cart = JSON.parse(localStorage.getItem('cart')) || [];
    const cartList = document.getElementById('cart-list');
    cartList.innerHTML = ""; // 清空現有的購物車內容

    cart.forEach((item, index) => {
        const cartItem = `
            <tr>
                <td>${item.name}</td>
                <td>${item.price}</td>
                <td>
                    <input type="number" min="1" max="10" value="${item.quantity}" onchange="updateQuantity(${index}, this.value)">
                </td>
                <td>${item.price * item.quantity}</td>
                <td>
                    <button class="btn btn-danger" onclick="removeFromCart(${index})">刪除</button>
                </td>
            </tr>
        `;
        cartList.innerHTML += cartItem;
    });
}

// 更新購物車中的產品數量
function updateQuantity(index, newQuantity) {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    if (newQuantity < 1) newQuantity = 1; // 確保數量至少為1
    if (newQuantity > 10) newQuantity = 10; // 確保數量不超過10
    cart[index].quantity = parseInt(newQuantity);
    localStorage.setItem('cart', JSON.stringify(cart));
    renderCart(); // 重新渲染購物車
}

// 從購物車中刪除產品
function removeFromCart(index) {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    cart.splice(index, 1); // 移除指定索引的產品
    localStorage.setItem('cart', JSON.stringify(cart));
    renderCart(); // 重新渲染購物車
}

// 確認購物車並將資料存入資料庫
function confirmCart() {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];

    // 假設這裡使用AJAX或Fetch API將資料送到後端儲存到資料庫
    fetch('/save-cart', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(cart)
    }).then(response => {
        if (response.ok) {
            alert('購物車已確認並儲存！');
            localStorage.removeItem('cart'); // 清空購物車
            renderCart(); // 重新渲染購物車
        } else {
            alert('儲存過程中發生錯誤，請稍後再試。');
        }
    }).catch(error => {
        console.error('Error:', error);
        alert('無法連接伺服器。');
    });
}

// 判斷當前頁面並呼叫相應的渲染函數
document.addEventListener('DOMContentLoaded', function () {
    if (document.getElementById('store-list')) {
        renderStoreList();
    } else if (document.getElementById('product-list')) {
        renderProductList();
    } else if (document.getElementById('cart-list')) {
        renderCart();
    }
});



