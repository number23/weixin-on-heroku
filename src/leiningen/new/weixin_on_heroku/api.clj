(ns {{name}}.api
  (:require [compojure.core :refer [defroutes GET POST]])
  (:require [clojure.string :as str])
  (:require [clojure.xml :as xml])
  (:use [org.clojars.number23.commons-lib.sha]))

(def TOKEN "weixin")
(def keywords #{:ToUserName
                :FromUserName
                :CreateTime
                :MsgType
                :Content
                :MsgId
                :PicUrl
                :Location_X
                :Location_Y
                :Scale
                :Label
                :Title
                :Description
                :Url
                :Event
                :EventKey})

(def text-tpl "<xml>
<ToUserName><![CDATA[%s]]></ToUserName>
<FromUserName><![CDATA[%s]]></FromUserName>
<CreateTime>%s</CreateTime>
<MsgType><![CDATA[text]]></MsgType>
<Content><![CDATA[%s]]></Content>
<FuncFlag>0</FuncFlag>
</xml>")

(defn check-signature
  [& {:keys [signature timestamp nonce]}]
  (let [s (sha-str (str/join (sort [TOKEN timestamp nonce])))]
    (if (= s signature) true false)))

(defn handler [body]
  (let [xml (xml/parse body)
        result (atom {})
        create-time (str (long (/ (System/currentTimeMillis) 1000)))
        res (atom "")]
    (doseq [x (xml-seq xml) :when (contains? keywords (:tag x))]
      (swap! result assoc (:tag x) (first (:content x))))
    (println @result)

    ;; default response
    (reset! res (format text-tpl
                        (:FromUserName @result)
                        (:ToUserName @result)
                        create-time
                        "对不起，不能回应您的消息。"))

    (if (and (= (:MsgType @result) "event") (= (:Event @result) "subscribe"))
      (reset! res (format text-tpl
                          (:FromUserName @result)
                          (:ToUserName @result)
                          create-time
                          "您好，谢谢关注！")))
    (if (and (= (:MsgType @result) "text") (= (:Content @result) "Hi"))
      (reset! res (format text-tpl
                          (:FromUserName @result)
                          (:ToUserName @result)
                          create-time
                          "Hello")))
    (println @res)
    @res))

(defroutes api-routes
  (GET "/" {{:strs [signature timestamp nonce echostr]} :query-params}
       (if (check-signature
            :signature signature
            :timestamp timestamp
            :nonce nonce) echostr ""))
  (POST "/" {{:strs [signature timestamp nonce]} :query-params body :body}
        (if (check-signature
            :signature signature
            :timestamp timestamp
            :nonce nonce) (handler body) "")))
