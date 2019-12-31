(ns countdown.core)

(require '[clj-time.local :as l])
(require '[clj-time.core :as t])
(require '[clj-time.coerce :as c])

(defn Formatted [numba]
  (def result (str numba))
  (if (< numba 10) (def result (str 0 numba)))
  result
)

(defn Countdown [new-year-2020 time-now]
  (def diff-second (quot (- new-year-2020 time-now) 1000))
  (def hour (quot diff-second (* 60 60)))
  (def minute (quot (- diff-second (* hour 60 60)) 60))
  (def secnd (- diff-second (+ (* hour 60 60) (* minute 60))))
  (def opo (if (< secnd 10) "yes" "no"))
  (printf "%s:%s:%s%n" (Formatted hour) (Formatted minute) (Formatted secnd))
)

(defn Now []
  (def new-year-2020 (atom (- (c/to-long "2020-01-01") (* 7 60 60 1000))))
  (def time-now (atom (c/to-long (l/local-now))))
  (while (> @new-year-2020 @time-now)
    (do
      (Countdown @new-year-2020 @time-now)
      (flush)
      (Thread/sleep 1000)
      (def time-now (atom (c/to-long (l/local-now))))
    )
  )
  (if (< @new-year-2020 @time-now) (println "Welcome 2020!"))
)

(defn dostuff []
  (do
    (print "I'm doing stuff")
    (flush)))

(defn -main
  "Let's count to 2020" 
  []
  (println "Let's count to 2020")
  (Now)
)
