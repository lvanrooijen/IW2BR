import { useEffect, useState } from 'react';
import { useParams, useSearchParams } from 'react-router';
import type { ITag } from '../../interfaces/environmentInterfaces';
import AxiosInstance from '../../util/services/AxiosInstance';

const TagDropdown: React.FC<Props> = ({ style }) => {
  const [tags, setTags] = useState<ITag[] | []>([]);
  const { environmentId } = useParams();

  useEffect(() => {
    AxiosInstance.get(`/environments/${environmentId}/tags`)
      .then((response) => console.log(response.data))
      .catch((error) => console.error(error));
  }, []);
  return (
    <div className={`${style}`}>
      <label className="hidden" htmlFor="myDropdown">
        Choose an option:
      </label>
      <select
        id="myDropdown"
        className="border-2 border-base-100 p-3 rounded-md w-full bg-amber-200 tracking-widest"
      >
        <option value="option1">Option 1</option>
        {tags.map((tag) => (
          <option key={tag.id} value={tag.id}>
            {tag.title}
          </option>
        ))}
      </select>
    </div>
  );
};

export default TagDropdown;

interface Props {
  style: string;
}
