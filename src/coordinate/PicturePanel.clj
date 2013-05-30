(ns coordinate.PicturePanel
  (:gen-class
    :extends javax.swing.JPanel
    :name coordinate.PicturePanel
    :exposes-methods {paintComponent parentPaintComponent})
  (:import (java.awt Toolkit)))

(defn -paintComponent [this g]
  (.parentPaintComponent this g)
  (let [
      toolkit (Toolkit/getDefaultToolkit)
      image (.getImage toolkit "test.jpg") ]
    (.drawImage g image 0 0 (.getWidth this) (.getHeight this) nil ))
  (.drawOval g 0 0 (.getWidth this) (.getHeight this)))
