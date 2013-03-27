(ns leiningen.new.weixin-on-heroku
  (:require [leiningen.new.templates :as t]))

(defn weixin-on-heroku
  "Generate a new Compojure project for use on Heroku."
  [name]
  (let [render (t/renderer "weixin-on-heroku")
        data {:name name
              :dir (t/sanitize name)
              :year (+ (.getYear (java.util.Date.)) 1900)
              :strs-get "{{:strs [signature timestamp nonce echostr]} :query-params}"
              :strs-post "{{:strs [signature timestamp nonce]} :query-params body :body}"}]
    (t/->files data
               [".gitignore" (render "gitignore" data)]
               ["Procfile" (render "Procfile" data)]
               ["README.md" (render "README.md" data)]
               ["src/{{dir}}/web.clj" (render "web.clj" data)]
               ["src/{{dir}}/api.clj" (render "api.clj" data)]
               ["resources/500.html" (render "500.html" data)]
               ["resources/404.html" (render "404.html" data)]
               ["test/{{dir}}/web_test.clj" (render "test.clj" data)]
               ["project.clj" (render "project.clj" data)]))
  (println "Generated new weixin-on-Heroku project in" name "directory."))
