(ns blog.area.main
  (:require
   [hiccup2.core :refer [html]]))

(defn index [request] (str (html [:html
                                  [:head [:title "My blog"]]
                                  [:body [:h1 "My blog"]]])))

(defn contact [request] (str (html [:html
                                    [:head [:title "Contact me"]]
                                    [:body [:h1 "Contact me"]]])))

(defn about [request] (str (html [:html
                                  [:head [:title "About me"]]
                                  [:body [:h1 "About me"]]])))

(defn login [request] (str (html [:html
                                  [:head [:title "Login"]]
                                  [:body [:h1 "Login"]]])))