(ns functional-di.gateway.stub-editor-gateway
  (:require [functional-di.gateway.editor-gateway :refer [IEditorGateway]]))

(deftype StubEditorGateway []
  IEditorGateway
  (workspace [_]
    {:config {:local-ci {:command.job.enable-pre-command false
                         :command.job.enable-post-command false}}})
  (make-terminal! [_ _]))
