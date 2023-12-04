(ns functional-di.build-agent-settings)

(defprotocol IBuildAgentSettings
  (set! [this])
  (intel-mac? [this]))

(deftype BuildAgentSettings [child-process-gateway
                             os-gateway]
  IBuildAgentSettings
  (set! [this]
    (when (intel-mac? this) (.spawn! child-process-gateway)))
  (intel-mac? [_]
    (and (= (.os-type os-gateway) "Darwin")
         (= (.arch os-gateway) "x64"))))
