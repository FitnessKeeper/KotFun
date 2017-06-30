package com.fitnesskeeper.kotfun;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.internal.Intrinsics;

public final class MoneyJava {
    private final double amount;
    @NotNull
    private final String currency;

    public final double getAmount() {
        return this.amount;
    }

    @NotNull
    public final String getCurrency() {
        return this.currency;
    }

    public MoneyJava(double amount, @NotNull String currency) {
//        Intrinsics.checkParameterIsNotNull(currency, "currency");
        super();
        this.amount = amount;
        this.currency = currency;
    }

    public final double component1() {
        return this.amount;
    }

    @NotNull
    public final String component2() {
        return this.currency;
    }

    @NotNull
    public final Money copy(double amount, @NotNull String currency) {
        Intrinsics.checkParameterIsNotNull(currency, "currency");
        return new Money(amount, currency);
    }

    // $FF: synthetic method
    // $FF: bridge method
    @NotNull
    public static Money copy$default(MoneyJava var0, double var1, String var2, int var3, Object var4) {
        if((var3 & 1) != 0) {
            var1 = var0.amount;
        }

        if((var3 & 2) != 0) {
            var2 = var0.currency;
        }

        return var0.copy(var1, var2);
    }

    public String toString() {
        return "Money(amount=" + this.amount + ", currency=" + this.currency + ")";
    }

    public int hashCode() {
        long var10000 = Double.doubleToLongBits(this.amount);
        return (int)(var10000 ^ var10000 >>> 32) * 31 + (this.currency != null?this.currency.hashCode():0);
    }

    public boolean equals(Object var1) {
        if(this != var1) {
            if(var1 instanceof MoneyJava) {
                MoneyJava var2 = (MoneyJava)var1;
                if(Double.compare(this.amount, var2.amount) == 0 && Intrinsics.areEqual(this.currency, var2.currency)) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }
}