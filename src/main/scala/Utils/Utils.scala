package Utils

object Utils {

  /**
   * Function modifies value by given function in map.
   *
   * @param m     map
   * @param k     key
   * @param f     modifying function
   * @tparam A
   * @tparam B
   * @return    modified map
   */
  def adjust[A, B](m: Map[A, B], k: A)(f: B => B) = m.updated(k, f(m(k)))

}
