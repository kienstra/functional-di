(ns functional-di.gateway.editor-gateway)

(defprotocol IEditorGateway
  (workspace [this])
  (make-terminal! [this command]))

(deftype EditorGateway []
  IEditorGateway
  (workspace [_]
    {:config {:local-ci {:command.job.enable-pre-command false
                         :command.job.enable-post-command false}}})
  (make-terminal! [_ _]))
