# Weixin-on-heroku Leiningen Template

A Leiningen template for weixin robot on Heroku platform.

The generated project has a few basics set up beyond the bare Compojure defaults:

* Cookie-backed session store
* Stack traces when in development
* Environment-based config via [environ](https://github.com/weavejester/environ)
* [HTTP-based REPL debugging](https://devcenter.heroku.com/articles/debugging-clojure) via [drawbridge](https://github.com/cemerick/drawbridge)
* lein-ring for development

## Usage
Activate WeiXin development model on [WeiXin Back-end](http://mp.weixin.qq.com),  
change `TOKEN` on `api.clj` file.

Weixin Interface config:

    URL: http://{{name}}.herokuapp.com/api
    Token: your token

    $ lein new weixin-on-heroku myapp
    $ cd myapp
    $ git init
    $ heroku apps:create myapp
    $ git add .
    $ git commit -m "Initial commit"
    $ git push heroku master
    $ curl http://myapp.herokuapp.com

See the [README of the generated project](https://github.com/number23/weixin-on-heroku/blob/master/src/leiningen/new/weixin_on_heroku/README.md) for details.

## Thanks
This repo init commit fork from [lein-heroku](https://github.com/technomancy/lein-heroku/tree/master/lein-template),  
thanks [technomancy](https://github.com/technomancy).

## License

Copyright © 2013 number23_cn <https://twitter.com/number23_cn>

Distributed under the Eclipse Public License, the same as Clojure.
