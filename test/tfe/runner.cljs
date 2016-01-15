(ns tfe.runner
  (:require [cljs.test :as test]
            [doo.runner :refer-macros [doo-all-tests doo-tests]]
            [tfe.core-test]))

(doo-tests 'tfe.core-test)
