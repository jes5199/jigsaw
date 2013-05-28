(ns coordinate.jigsaw)
(import '(javax.swing JPanel JFrame JButton JTextField JScrollPane
                    JLabel Timer SwingUtilities))

(import '(coordinate PicturePanel))

(defn text-field [value]
  (doto (JTextField. value 15)
    (.setEnabled false)
    (.setHorizontalAlignment JTextField/RIGHT)))

(defmacro with-action [component event & body]
  `(. ~component addActionListener
      (proxy [java.awt.event.ActionListener] []
        (actionPerformed [~event] ~@body))))

(defn jigsaw-app []
  (doto (JFrame. "Jigsaw")
    (.setContentPane
      (doto (JPanel.)
        (.add (JScrollPane. (PicturePanel.)))))
    (.pack)
    (.setVisible true)))

(defn -main [& args]
  (jigsaw-app))
