(ns coordinate.PicturePanel
  (:gen-class
    :extends javax.swing.JPanel
    :name coordinate.PicturePanel
    :exposes-methods {paintComponent parentPaintComponent}))

(defn -paintComponent [this g]
  (.parentPaintComponent this g))
