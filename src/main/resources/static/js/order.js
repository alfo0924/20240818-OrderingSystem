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
            { img: "shop4_4.jpg", name: "放山土雞雞胸肉", price: 125},
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
            { img: "shop5_4.jpg", name: "拉拉山水蜜桃", price: 220},
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
            { img: "shop6_2.jpg", name: "粉紅佳人", price: 320 },
            { img: "shop6_3.jpg", name: "四劍客", price: 888 },
            { img: "shop6_4.jpg", name: "蘇格蘭紅茶", price: 170},
            { img: "shop6_5.jpg", name: "西西里檸檬", price: 180 },
            { img: "shop6_6.jpg", name: "大摩15", price: 400 },
            { img: "shop6_7.jpg", name: "心痛的感覺", price: 200 },

        ]
    },


];




//     "shop6 , 撂佬大飲料料料店"

// 渲染店家列表
function renderStoreList() {
    const storeList = document.getElementById('store-list');
    shopitems.forEach(store => {
        const storeCard = `
            <div class="col-md-4">
                <div class="card">
                    <img src="../static/imgs/${store.information[0].img}" class="card-img-top" alt="${store.information[0].name}">
                    <div class="card-body">
                        <h5 class="card-title">${store.information[0].name}</h5>
                        <a href="order-list.html?shopId=${store.id}" class="btn btn-primary">View Products</a>
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
                        <img src="../static/imgs/${product.img}" class="card-img-top" alt="${product.name}">
                        <div class="card-body">
                            <h5 class="card-title">${product.name}</h5>
                            <p class="card-text">$${product.price}</p>
                            <button class="btn btn-success" onclick="addToCart('${product.name}', ${product.price})">Add to Cart</button>
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

// 判斷當前頁面，並根據頁面調用對應的渲染函數
document.addEventListener('DOMContentLoaded', function() {
    if (document.getElementById('store-list')) {
        renderStoreList();
    }
    if (document.getElementById('product-list')) {
        renderProductList();
    }
});
