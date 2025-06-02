(ns blog.style
  (:require [garden.core :refer [css]]))

(def text-style
  (css [
        :body {
               :font-family "Arial"
               :color       "blue"
               }
        ])
  )
