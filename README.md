# Weixin-on-heroku Leiningen Template

A Leiningen template for weixin robot on Heroku platform.

The generated project has a few basics set up beyond the bare Compojure defaults:

* Cookie-backed session store
* Stack traces when in development
* Environment-based config via [environ](https://github.com/weavejester/environ)
* [HTTP-based REPL debugging](https://devcenter.heroku.com/articles/debugging-clojure) via [drawbridge](https://github.com/cemerick/drawbridge)
* lein-ring for development

## Usage

    $ lein new heroku myapp
    $ cd myapp
    $ git init
    $ heroku apps:create myapp
    $ git add .
    $ git commit -m "Initial commit"
    $ git push heroku master
    $ curl http://myapp.herokuapp.com

See the [README of the generated project](https://github.com/number23/weixin-on-heroku/blob/master/src/leiningen/new/weixin-on-heroku/README.md) for details.

## License

Copyright © 2013 number23_cn <https://twitter.com/number23_cn>

Distributed under the Eclipse Public License, the same as Clojure.
