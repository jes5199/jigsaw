(ns coordinate.jigsaw
  (:import (javax.swing JPanel JFrame JButton JTextField JScrollPane
                     JLabel Timer SwingUtilities))
  (:import (java.awt GridBagLayout GridBagConstraints))

  (:import (coordinate PicturePanel))
)

(defn text-field [value]
  (doto (JTextField. value 15)
    (.setEnabled false)
    (.setHorizontalAlignment JTextField/RIGHT)))

(defmacro with-action [component event & body]
  `(. ~component addActionListener
      (proxy [java.awt.event.ActionListener] []
        (actionPerformed [~event] ~@body))))

(defn jigsaw-app []
  (let [
      gbc (GridBagConstraints.)
      gbl (GridBagLayout.)
      picture-panel (PicturePanel. gbl)
      scroll-pane (JScrollPane. picture-panel)
      ]
    (set! (. gbc fill) (GridBagConstraints/BOTH))
    (set! (. gbc weightx) 1)
    (set! (. gbc weighty) 1)
    (.setConstraints gbl scroll-pane gbc)
    (doto (JFrame. "Jigsaw")
      (.setContentPane
        (doto (JPanel. gbl)
          (.add scroll-pane)))
      (.pack)
      (.setVisible true))))

(defn -main [& args]
  (jigsaw-app))
