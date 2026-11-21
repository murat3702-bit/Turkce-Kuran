KUR'AN MEALLERİ - Android projesi (WebView, çevrimdışı)

Derleme (yerel):
1) Android Studio (Koala veya daha yeni, JDK 17) ile bu klasörü açın.
2) Gradle Sync bitince Build > Build APK(s) veya Run ile derleyin.

Sürüm (otomatik):
- GitHub'a main dalına her push'ta .github/workflows/build-apk.yml çalışır;
  app/build.gradle içindeki versionCode ve versionName (2.15 -> 2.16) +1 artırılır,
  depoya kaydedilir ([skip ci]) ve APK bu yeni sürümle derlenip Release olarak yayınlanır.
- Sürümü elle artırmayın; aksi halde çift artar.
- Hakkında ekranındaki "Ver ..." yazısı uygulamanın gerçek versionName değerinden okunur.
- Yerel (Android Studio) derlemeler sürümü artırmaz, yalnızca CI artırır.

Arayüz: app/src/main/assets/index.html (tek dosya, meal verileri içinde).
Uygulama adı: res/values/strings.xml, simge: res/mipmap-*/ic_launcher.png
applicationId: com.mb.turkcekuran.app
