import matplotlib.pyplot as plt
import math

def normal(x: float, m: float, sigma: float) -> float:
    """
    Normal distribution function
    :param x: x random variable
    :param m: mean
    :param sigma: standard deviation
    :return: normal distribution value
    """
    return (1 / math.sqrt(2 * math.pi) / sigma) * math.exp(
        -(x - m) * (x - m) / 2 / sigma / sigma)

def readData(filename: str) -> tuple[list[float], list[float]]:
    """
    Read data from file
    :param filename: file name
    :return: x and y list
    """
    with open(filename, "r") as f:  # read data from file
        lines: list[str] = f.readlines()
    xl: list[float] = list()
    yl: list[float] = list()
    for l in lines:  # split data into x and y and store them in list
        x, y = l.split()
        xl.append(float(x))
        yl.append(float(y))
    return xl, yl

def drawHistogram(
    prefix: str,
    xmin: float, xmax: float, mean: float, sigma: float,
    w=1.5, t=1000, n=1024, toPDF = False) -> None:
    """
    Draw histogram
    :param prefix: prefix for file name
    :param xmin: x minimum
    :param xmax: x maximum
    :param mean: mean of the distribution
    :param sigma: standard deviation of the distribution
    :param w: width of the bar
    :param t: number of steps
    :param n: number of points for drawing the normal distribution
    """
    dataFilename = f"{prefix}-output-{t}.txt"  # file name for input data
    xl, yl = readData(dataFilename)
    fig, ax = plt.subplots()
    ax.bar(xl, yl, w)
    ax.set_title(f"Histogram for {prefix} Case ($t={t}$)")
    ax.set_xlabel("$x$")
    xld: list[float] = [(xmax - xmin) * x / n + xmin for x in range(n)]
    yld: list[float] = [normal(x, mean, sigma) for x in xld]
    ax.plot(xld, yld, c="r", linewidth=2)
    plt.show()
    if toPDF:
        pdfFileName = f"{prefix}-histogram-{t}.pdf"
        fig.savefig(pdfFileName)


if __name__ == "__main__":
    t = 1000
    # plt.rcParams['font.size']=28
    plt.rcParams["font.family"] = "Times New Roman"
    plt.rcParams["mathtext.fontset"] = "cm"
    plt.rcParams["mathtext.default"] = "it"
    toPDF = True
    # drawHistogram("Histogram", -150, 150, 0, math.sqrt(t), w=1.8, t=t, toPDF=toPDF)
    # drawHistogram('Uniform',-40,40,0,math.sqrt(t/12),w=.8,t=t, toPDF=toPDF)
    drawHistogram('Exponential',800,1200,t,math.sqrt(t),t=t,w=.8,toPDF=toPDF)   
