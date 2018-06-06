package com.milosnikolik.backendChallenge.Model;

import java.util.DoubleSummaryStatistics;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * Created by User on 07.6.2018.
 */
public class Timing {

        private final long timestamp;
        private final DoubleSummaryStatistics statistics;

        public Timing(long timestamp, DoubleSummaryStatistics statistics) {
            this.timestamp = timestamp;
            this.statistics = requireNonNull(statistics);
        }

        public Timing() {
            this(-1, new DoubleSummaryStatistics());
        }

        public long getTimestamp() {
            return timestamp;
        }

        public DoubleSummaryStatistics getStatistics() {
            return statistics;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Timing that = (Timing) o;
            return Objects.equals(timestamp, that.timestamp) &&
                    Objects.equals(statistics, that.statistics);
        }

        @Override
        public int hashCode() {
            return Objects.hash(timestamp, statistics);
        }
}
