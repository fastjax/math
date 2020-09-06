/* Copyright (c) 2020 Seva Safris, LibJ
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * You should have received a copy of The MIT License (MIT) along with this
 * program. If not, see <http://opensource.org/licenses/MIT/>.
 */

package org.libj.math;

import java.math.BigDecimal;

import org.junit.Test;
import org.libj.math.Decimals.Decimal;

public class DecimalMultiplicationTest extends DecimalTest {
  @Test
  public void testMul() {
    final long defaultValue = random.nextLong();
    for (byte i = Decimal.MIN_SCALE_BITS; i <= MAX_SCALE_BITS; ++i) {
      final byte scaleBits = i;
      test("mul(" + scaleBits + ")").withSkip(skip(scaleBits)).withEpsilon(BigDecimal.ZERO).withAuditReport(null).withCases(scaleBits,
        d(BigDecimal.class, this::toBigDecimal, (BigDecimal a, long b) -> a.multiply(toBigDecimal(b)), o -> o),
        d(Decimal.class, this::toDecimal, (Decimal a, long b) -> a.mul(toDecimal(b)), o -> o),
        d(long.class, (long a, long b) -> Decimal.mul(a, b, defaultValue, scaleBits), (long o) -> o == defaultValue ? null : o)
      );
    }
  }
}