# 點餐系統

這是一個基於Spring Boot開發的點餐系統，旨在提供一個輕鬆便捷的在線點餐平台。

## 技術棧

- 後端框架：Spring Boot
- 前端模板引擎：Thymeleaf
- 數據庫：(未提供信息，可能使用JPA或MyBatis進行數據庫操作)
- 日誌：SLF4J
- 前端框架：Bootstrap 4

## 主要功能

1. 用戶登錄
2. 訂單創建
3. 購物車管理
4. 訂單列表查看
5. 產品管理

## 項目結構

- `controller`: 包含處理HTTP請求的控制器類
- `model`: 包含數據模型類
- `service`: 包含業務邏輯服務類
- `repository`: 包含數據訪問層接口
- `resources/templates`: 包含Thymeleaf模板文件

## 主要類說明

- `OrderController`: 處理訂單相關的請求
- `OrderService`: 提供訂單相關的業務邏輯
- `Order`: 訂單實體類
- `OrderItem`: 訂單項實體類

## 如何運行

1. 確保您的開發環境中安裝了Java和Maven
2. 克隆此存儲庫
3. 在項目根目錄執行 `mvn spring-boot:run`
4. 訪問 `http://localhost:8080` 開始使用系統

## 注意事項

- 請確保正確配置數據庫連接
- 系統目前處於開發階段，可能存在一些未完善的功能

## 未來計劃

- 完善用戶認證和授權機制
- 添加更多的訂單管理功能
- 優化用戶界面和用戶體驗

## 貢獻

歡迎提交問題和拉取請求，共同改進這個項目！

## 許可證

[MIT License](LICENSE)
