(ns functional-di.command-decorators)

(defn make-command-decorators [editor-gateway]
  (fn command-decorators []
    (let [pre-command?   (:command.job.enable-pre-command (:local-ci (:config (.workspace editor-gateway))))
          post-command? (:command.job.enable-post-command (:local-ci (:config (.workspace editor-gateway))))]
      {:pre-command       (if pre-command? "echo 'Please enter what you want to run before the job command (this will appear in stdout):'; read pre_command;" "")
       :post-command      (if post-command? "echo 'Please enter what you want to run after the job command, followed by enter (you will not see anything as you type):'; read -s post_command;" "")
       :eval-pre-command  (if pre-command? "eval $pre_command" "")
       :eval-post-command (if post-command? "eval $post_command" "")})))
