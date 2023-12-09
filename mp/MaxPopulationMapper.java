import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxPopulationMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

    // Skip the header line
    if (key.get() == 0) {
      return;
    }

    String line = value.toString();
    String country = line.split(",")[3]; // Country is in the third column
    int population2020 = Integer.parseInt(line.split(",")[10]); // 2020 Population is in the seventh column

    context.write(new Text(country), new IntWritable(population2020));
  }
}

