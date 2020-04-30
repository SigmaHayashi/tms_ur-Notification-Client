# tms_ur Notification Client

ROS-TMS for Smart Previewed Reality (tms_ur_notification)から発行されるプッシュ通知を受け取るためのAndroidアプリケーション


## 関連ソフトのリンク

[ROS-TMS for Smart Previewed Reality](https://github.com/SigmaHayashi/ros_tms_for_smart_previewed_reality)

[tms_ur_notification](https://github.com/SigmaHayashi/ros_tms_for_smart_previewed_reality/tree/master/tms_ur/tms_ur_notification)

[Smart Previewed Reality](https://github.com/SigmaHayashi/Smart-Previewed-Reality)


# 開発環境

PC : Windows10 64bit

* Android Studio 3.5.1

Androidスマートフォン : Pixel 4 XL (Android 10)


# 導入方法

## Firebaseプロジェクトの作成
[tms_ur_notification](https://github.com/SigmaHayashi/ros_tms_for_smart_previewed_reality/tree/master/tms_ur/tms_ur_notification)の「Firebaseの導入/Firebaseプロジェクトの作成」にあるようにFirebaseプロジェクトを作成する


## Firebaseプロジェクトとアプリを接続する
1. 作成したFirebaseプロジェクトのコンソール画面から，Androidのアプリケーションと接続するためにドロイド君のアイコンをクリック

1. アプリ登録画面が表示されるので，Androidアプリケーションのパッケージ名を入力する

    ※ここでAndroid Studioを起動しておく

1. 次に進むとConfigファイルをダウンロードできるので，そのファイルを指示通りの位置に配置する（ros_tms Notification Client/appの中）

1. 次に進むと接続のために必要なコードが表示されるので，指示通りにコードを追加する．（追加済みだが，不足してあるものがあれば適宜追加）このときにコピペしたコードに黄色の線が引かれて警告が出る．[Alt + Enter]でエラー詳細を見てバージョン名等を適宜変更する．また，右上に表示される"Sync Now"をクリックして変更を適用する

1. 指定されたコードをすべて追加し終えて次に進むと，Androidアプリケーションを起動するように表示されるので，Android Studioでアプリをビルド＆インストールして実機上でアプリケーションを起動する．このとき使用するAndroidスマートフォンはインターネットに接続しておく必要がある．

1. アプリケーション起動後しばらくするとFirebaseプロジェクトのコンソールに設定完了の旨が表示される．これでFirebaseプロジェクトとAndroidアプリケーションの接続が完了する．

参考 : https://blog.codecamp.jp/programming-first-android-app-development


## 通知機能の動作確認
Firebaseプロジェクトのコンソール画面からGrow > Cloud Messageと進み，送信先アプリケーション，通知タイトル等を指定して通知を送信する．

通知を受信できればOK

tms_ur_notificationから発行されるプッシュ通知を受信する準備が完了


# 使い方
ビルド後，一度アプリケーションを起動することでROS-TMS for Smart Previewed Realityからの通知を受け取る設定が完了する．

Smart Previewed Realityをインストールしている場合，アプリ内の起動ボタンを押すとSmart Previewed Realityを起動できる．

また，tms_ur_notificationから発行された通知にSmart Previewed Realityの起動が指定されている場合，Androidスマートフォンに届いた通知をタップすることでSmart Previewed Realityを起動することができる．
