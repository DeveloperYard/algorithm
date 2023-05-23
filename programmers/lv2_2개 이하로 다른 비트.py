def minNum(num):
    if num % 2 == 0:
        return num + 1
    else:
        for i in range(len(bin(num)[2:])+1):
            val = num + 2**i
            if bin(val ^ num).count('1') > 2:
                continue
            else:
                return val


def solution(numbers):
    answer = []

    for i in range(len(numbers)):
        answer.append(minNum(numbers[i]))

    return answer