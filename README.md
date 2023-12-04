# Functional DI

An example of Dependency Injection in Clojure, without a DI library.

The [composition root](src/functional_di/composition_root.clj) injects the dependencies.

Here's a [detailed post](https://ryankienstra.com/dependency-injection-in-clojure) on how this works.

The production gateways, like [editor_gateway.clj](src/functional_di/gateway/editor_gateway.clj), don't actually work.

This repo is just to show how to do Dependency Injection in Clojure.

But the unit tests are complete, and pass.

## Run unit tests

```sh
lein test
```

## License

[GPLv2](https://github.com/kienstra/data-store/blob/develop/LICENSE) or later
