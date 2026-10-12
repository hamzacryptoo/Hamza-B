# متصفح HAMZA

متصفح Android مفتوح المصدر بسيط يحجب الإعلانات والمتتبعات، ولا يعرض أي إشعارات.

## المميزات
- حجب الإعلانات عبر قائمة نطاقات في AdBlocker.java
- لا يطلب إذن الإشعارات إطلاقاً (لا POST_NOTIFICATIONS في Manifest)
- منع الموقع الجغرافي، HTTPS فقط
- صفحة رئيسية DuckDuckGo

## البناء
### عبر GitHub Actions (مجاناً)
1. ارفع المجلد على GitHub
2. أنشئ .github/workflows/build.yml بالمحتوى المرفق في التعليمات
3. بعد الـ push حمّل APK من Actions > Artifacts

### عبر Android Studio
1. ثبت Android Studio، افتح المجلد
2. Build > Generate Signed Bundle/APK > APK
3. ستجده في app/release/

## الترخيص: MIT
